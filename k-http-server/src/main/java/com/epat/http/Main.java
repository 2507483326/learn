package com.epat.http;

import com.epat.http.server.NioServer;

import java.io.IOException;

/**
 * @author 李涛
 * @date : 2021/6/28 20:27
 */
public class Main {

    public static void main(String[] args) throws IOException {
        NioServer nioServer = new NioServer(9000);
        nioServer.start();
    }

}
