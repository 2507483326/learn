package com.epat.http.web;

import com.epat.http.parse.HttpEntry;

import java.nio.channels.SocketChannel;

/**
 * @author 李涛
 * @date : 2021/6/30 9:50
 */
public interface WebService {

    public void doHttp(SocketChannel socketChannel, HttpEntry httpEntry);

}
