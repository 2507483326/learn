package com.epat.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * @author 李涛
 * @date : 2021/6/28 21:00
 */
public class Test {

    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        Selector sl = Selector.open();
        try {
            socketChannel.configureBlocking(false);
            socketChannel.socket().bind(new InetSocketAddress(8899));
            socketChannel.register(sl, SelectionKey.OP_CONNECT | SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            InetAddress addr = InetAddress.getByName(null);
            //连接addr和port对应的服务器
            boolean success = socketChannel.connect(new InetSocketAddress(addr, 9000));
            if(!success) {
                socketChannel.finishConnect();
            }
            while (true) {
                sl.select();
                Iterator<SelectionKey> it = sl.selectedKeys().iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    it.remove();
                    if(key.isConnectable() && !socketChannel.isConnected()) {
                        System.out.println("连接成功!!!!");
                    }
                    if(key.isWritable()) {
                        socketChannel.write(ByteBuffer.wrap(new String("hello").getBytes()));
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
