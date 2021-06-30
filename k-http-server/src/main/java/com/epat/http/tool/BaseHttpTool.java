package com.epat.http.tool;

import com.epat.http.constant.HttpConstant;
import com.epat.http.exception.HttpException;
import com.epat.http.parse.HttpHeader;
import com.epat.http.web.HttpTypeTranslate;
import com.epat.http.web.Resource;
import com.epat.http.web.WebStaticResource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author 李涛
 * @date : 2021/6/30 10:04
 */
public class BaseHttpTool {
    public static Logger logger =  LogManager.getLogger(BaseHttpTool.class);

    private static String LINE_SPACE = "\r\n";

    public static void sendSuccess (SocketChannel socketChannel, HttpHeader httpHeader, File file) {
        try (FileInputStream is = new FileInputStream(file);
             FileChannel ic = is.getChannel();) {
            logger.info("Client[" + socketChannel.socket().getRemoteSocketAddress() + "]开始返回数据");
            StringBuilder stringBuilder = new StringBuilder(httpHeader.getVersion() + " " + HttpConstant.State.SUCCESS.getCode() + " " + HttpConstant.State.SUCCESS.getMessage() + LINE_SPACE);
            stringBuilder.append(genBaseHeader(httpHeader, file));
            stringBuilder.append(LINE_SPACE);
            byte [] sendResult = stringBuilder.toString().getBytes(StandardCharsets.UTF_8);
            ByteBuffer byteBuffer =  ByteBuffer.wrap(sendResult);
            write(socketChannel, byteBuffer);
            ByteBuffer fileBuffer = ByteBuffer.allocateDirect(4096);
            while (ic.read(fileBuffer) != -1) {
                ByteBuffer fileByteBuffer = (ByteBuffer)fileBuffer.flip();
                write(socketChannel, fileByteBuffer);
                fileBuffer.clear();
            }
            logger.info("Client[" + socketChannel.socket().getRemoteSocketAddress() + "]返回数据完成");
        } catch (Exception e) {
            logger.error("发送数据失败", e);
            throw new RuntimeException("发送数据失败");
        } finally {
            try {
                if (socketChannel.isOpen()) {
                    socketChannel.close();
                }
            } catch (IOException e) {
                logger.error("关闭连接失败", e);
            }
        }
    }

    public static void sendError (SocketChannel socketChannel, HttpHeader httpHeader, HttpException httpException) {
        try {
            logger.info("Client[" + socketChannel.socket().getRemoteSocketAddress() + "]开始返回数据");
            StringBuilder stringBuilder = new StringBuilder(httpHeader.getVersion() + " " + httpException.getCode() + " " + httpException.getMessage() + LINE_SPACE);
            stringBuilder.append(genBaseHeader(httpHeader, null));
            stringBuilder.append(LINE_SPACE);
            byte [] sendResult = stringBuilder.toString().getBytes(StandardCharsets.UTF_8);
            ByteBuffer byteBuffer =  ByteBuffer.wrap(sendResult);
            write(socketChannel, byteBuffer);
            logger.info("Client[" + socketChannel.socket().getRemoteSocketAddress() + "]返回数据完成");
        } catch (Exception e) {
            logger.error("发送数据失败", e);
            throw new RuntimeException("发送数据失败");
        } finally {
            try {
                if (socketChannel.isOpen()) {
                    socketChannel.close();
                }
            } catch (IOException e) {
                logger.error("关闭连接失败", e);
            }
        }
    }

    public static void sendError (SocketChannel socketChannel, HttpHeader httpHeader) {
        sendError(socketChannel, httpHeader, HttpException.HTTP_SERVER_EXCEPTION);
    }

    public static StringBuilder genBaseHeader (HttpHeader httpHeader, File file) {
        StringBuilder stringBuilder = new StringBuilder();
        Map<String, String> baseDataMap = new HashMap<>(10);
        baseDataMap.put("date", new Date().toString());
        if (file != null) {
            baseDataMap.put("content-Type", HttpTypeTranslate.doTranslate(file.getName()));
            baseDataMap.put("content-length", String.valueOf(file.length()));
        }
        baseDataMap.forEach((k, v) -> {
            stringBuilder.append(k + ":" + v + LINE_SPACE);
        });
        return stringBuilder;
    }

    public static void write (SocketChannel socketChannel, ByteBuffer byteBuffer) throws InterruptedException, IOException {
        int isWrit = socketChannel.write(byteBuffer);
        if (isWrit == 0) {
            while (isWrit == 0) {
                Thread.sleep(200);
                isWrit = socketChannel.write(byteBuffer);
            }
        }
    }

}
