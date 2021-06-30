package com.epat.http.server;

import com.epat.http.parse.HttpBuilder;
import com.epat.http.parse.HttpEntry;
import com.epat.http.tool.BaseHttpTool;
import com.epat.http.tool.ByteTool;
import com.epat.http.web.WebStaticResource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author 李涛
 * @date : 2021/6/28 20:29
 */
public class NioServer {
    public static Logger logger =  LogManager.getLogger(NioServer.class);

    private Integer port;

    private ThreadPoolExecutor threadPoolExecutor = null;

    public NioServer(Integer port) {
        this.port = port;
        threadPoolExecutor = new ThreadPoolExecutor(3, 10, 100, TimeUnit.SECONDS, new ArrayBlockingQueue(100), Executors.defaultThreadFactory(), (i, t) -> {
            // 这里可以放入外部Queue
            if (!t.isShutdown()) {

            }
        });
    }

    public void start () throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        Selector selAccept = Selector.open();
        Selector selHttpHandler = Selector.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        serverSocketChannel.register(selAccept, SelectionKey.OP_ACCEPT);
        AcceptNioRunnable acceptRun = new AcceptNioRunnable(selAccept, selHttpHandler, serverSocketChannel);
        new Thread(acceptRun).start();
        HttpNioRunnable readRun = new HttpNioRunnable(selHttpHandler);
        new Thread(readRun).start();
    }

    class AcceptNioRunnable implements Runnable {

        private Selector selAccept;
        private Selector selReadHandler;
        private ServerSocketChannel serverSocketChannel;

        public AcceptNioRunnable(Selector selAccept, Selector selReadHandler, ServerSocketChannel serverSocketChannel) {
            this.selAccept = selAccept;
            this.selReadHandler = selReadHandler;
            this.serverSocketChannel = serverSocketChannel;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    if (selAccept.selectNow() == 0) {
                        continue;
                    };
                    Iterator<SelectionKey> it = selAccept.selectedKeys().iterator();
                    while (it.hasNext()) {
                        SelectionKey sKey = it.next();
                        it.remove();
                        if (sKey.isAcceptable()) {
                            SocketChannel socketChannel = this.serverSocketChannel.accept();
                            socketChannel.configureBlocking(false);
                            socketChannel.register(this.selReadHandler, SelectionKey.OP_READ);
                            System.out.println("client:" + socketChannel.socket().getPort());
                            logger.info("Client" + socketChannel.socket().getInetAddress() + "建立连接");
                        }
                    }
                } catch (Exception e) {
                    logger.error(e);
                    e.printStackTrace();
                }
            }
        }
    }

    class HttpNioRunnable implements Runnable {
        private Selector selHttpHandler;
        public HttpNioRunnable ( Selector selHttpHandler) {
            this.selHttpHandler = selHttpHandler;
        }
        @Override
        public void run() {
            ByteBuffer buffer = ByteBuffer.allocate(1024 * 10);
            while (true) {
                try {
                    if (this.selHttpHandler.selectNow() == 0) {
                        continue;
                    }
                    Iterator<SelectionKey> it =  this.selHttpHandler.selectedKeys().iterator();
                    HttpBuilder httpBuilder = new HttpBuilder();
                    while (it.hasNext()) {
                        SelectionKey sKey = it.next();
                        it.remove();
                        if (sKey.isReadable()) {
                            SocketChannel socketChannel = (SocketChannel) sKey.channel();
                            if (!socketChannel.isOpen()) {
                                return;
                            }
                            logger.info("Client[" + socketChannel.socket().getRemoteSocketAddress() + "]开始接受数据");
                            String clientData = "";
                            String headData = null;
                            byte [] originHeader = new byte[1024];
                            byte [] originBody = new byte[1024];
                            int headIndex = 0;
                            int bodyIndex = 0;
                            while (socketChannel.read(buffer) > 0) {
                                for (int i = 0; i < buffer.position(); i++) {
                                   byte b = buffer.get(i);
                                   if (headData == null) {
                                       originHeader[headIndex++] = b;
                                       if (originHeader.length == headIndex) {
                                           originHeader = ByteTool.byteExpansion(originHeader, 1024);
                                       }
                                       if (originHeader[headIndex - 1] == '\n' && originHeader[headIndex - 2] == '\r' && originHeader[headIndex - 3] == '\n' && originHeader[headIndex - 4] == '\r') {
                                           headData = new String(originHeader);
                                           httpBuilder.header(headData);
                                       }
                                   } else {
                                       if (originBody[bodyIndex -1] == '\n' && originBody[bodyIndex - 2] == '\r') {
                                           clientData = new String(originBody);
                                           httpBuilder.body(clientData);
                                           socketChannel.close();
                                       } else {
                                           originBody[bodyIndex++] = b;
                                           if (originBody.length == bodyIndex) {
                                               originBody = ByteTool.byteExpansion(originBody, 1024);
                                           }
                                       }
                                   }
                                }
                                buffer.clear();
                            }
                            logger.info("Client[" + socketChannel.socket().getRemoteSocketAddress() + "]接受数据完成");
                            threadPoolExecutor.execute(new HttpHandlerRunnable(socketChannel, httpBuilder.build()));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        class HttpHandlerRunnable implements Runnable {

            private SocketChannel socketChannel;

            private HttpEntry httpEntry;

            public HttpHandlerRunnable(SocketChannel socketChannel, HttpEntry httpEntry) {
                this.socketChannel = socketChannel;
                this.httpEntry = httpEntry;
            }

            @Override
            public void run() {
                WebStaticResource webStaticResource = new WebStaticResource(NioServer.class.getClassLoader().getResource("template").getPath());
                webStaticResource.doHttp(socketChannel, httpEntry);
            }
        }
    }


}
