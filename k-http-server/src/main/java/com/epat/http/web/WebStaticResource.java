package com.epat.http.web;

import com.epat.http.exception.HttpException;
import com.epat.http.parse.HttpEntry;
import com.epat.http.tool.BaseHttpTool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * @author 李涛
 * @date : 2021/6/30 9:49
 */
public class WebStaticResource implements WebService{
    public static Logger logger =  LogManager.getLogger(WebStaticResource.class);

    private String baseUrl;

    public WebStaticResource(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public void doHttp(SocketChannel socketChannel, HttpEntry httpEntry) {
        try {
            String url = httpEntry.getHttpHeader().getUrl();
            if (url == null || url.equals("/")) {
                url = "/index.html";
            }
            if (url.indexOf("?") > 0) {
                url = url.substring(0, url.indexOf("?"));
            }
            File file = new File(baseUrl + url);
            if (!file.exists() || file.isDirectory()) {
                throw HttpException.HTTP_404_EXCEPTION;
            }
            BaseHttpTool.sendSuccess(socketChannel, httpEntry.getHttpHeader(), file);
        } catch (HttpException httpException) {
            logger.error(httpException);
            BaseHttpTool.sendError(socketChannel, httpEntry.getHttpHeader(), httpException);
        } catch (Exception e) {
            logger.error(e);
            BaseHttpTool.sendError(socketChannel, httpEntry.getHttpHeader());
        }
    }

}
