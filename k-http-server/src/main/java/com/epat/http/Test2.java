package com.epat.http;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;

/**
 * @author 李涛
 * @date : 2021/6/28 21:12
 */
public class Test2 {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        Selector sel = Selector.open();
        ByteBuffer buffer = ByteBuffer.allocate(16);
        try {
            ssc.configureBlocking(false);
            ssc.socket().bind(new InetSocketAddress(9000));
            ssc.register(sel, SelectionKey.OP_ACCEPT);
            AcceptHandlerRunnable acceptHandlerRunnable = new AcceptHandlerRunnable(sel, ssc);
            new Thread(acceptHandlerRunnable).start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}



class AcceptHandlerRunnable implements Runnable {

    private Selector selAccept;
    private ServerSocketChannel serverSocketChannel;

    public AcceptHandlerRunnable(Selector selAccept, ServerSocketChannel serverSocketChannel) {
        this.selAccept = selAccept;
        this.serverSocketChannel = serverSocketChannel;
    }

    @Override
    public void run() {
        while (true) {
            // 没有事件发生时阻塞
            try {
                selAccept.select();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Iterator<SelectionKey> it = selAccept.selectedKeys().iterator();
            while (it.hasNext()) {
                SelectionKey sKey = it.next();
                it.remove();
                if (sKey.isAcceptable()) {
                    SocketChannel socketChannel = null;
                    try {
                        socketChannel = serverSocketChannel.accept();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        socketChannel.configureBlocking(false);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        socketChannel.register(selAccept, SelectionKey.OP_READ);
                    } catch (ClosedChannelException e) {
                        e.printStackTrace();
                    }
                    System.out.println("client:" + socketChannel.socket().getPort());
                } else {
                       /* SocketChannel socketChannel = (SocketChannel) sKey.channel();
                        socketChannel.read(buffer);
                        Charset cs = Charset.forName("utf-8");
                        CharBuffer cb = cs.decode((ByteBuffer)buffer.flip());
                        System.out.println("接收到数据：" + cb);
                        buffer.clear();*/
                }
            }
        }
    }
}