package com.epat.http.web;

/**
 * @author 李涛
 * @date : 2021/6/30 10:17
 */
public class HttpTypeTranslate {

    public static String doTranslate (String fileName) {
        if (fileName == null || fileName.equals("")) {
            return "text/plain; charset=utf-8";
        }
        int index = fileName.lastIndexOf(".");
        String fileType = fileName.substring(index);
        if (fileType.equals(".html")) {
            return "text/html; charset=utf-8";
        }
        if (fileType.equals(".css")) {
            return "text/css; charset=utf-8";
        }
        if (fileType.equals(".js")) {
            return "application/x-javascript; charset=utf-8";
        }
        if (fileType.equals(".png")) {
            return "image/png";
        }
        if (fileType.equals(".jpg")) {
            return "image/jpg";
        }
        if (fileType.equals(".woff")) {
            return "font/woff2";
        }
        if (fileType.equals(".woff2")) {
            return "font/woff2";
        }
        if (fileType.equals(".svg")) {
            return "image/svg+xml";
        }
        return "text/plain; charset=utf-8";
    }

}
