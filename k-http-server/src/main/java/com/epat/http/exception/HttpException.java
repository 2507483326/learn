package com.epat.http.exception;

import lombok.Data;

/**
 * @author 李涛
 * @date : 2021/6/30 9:54
 */
@Data
public class HttpException extends RuntimeException{

    private Integer code;

    public HttpException (Integer code, String message) {
        super(message);
        this.code = code;
    }

    public static final HttpException HTTP_404_EXCEPTION = new HttpException(404, "文件不存在");
    public static final HttpException HTTP_SERVER_EXCEPTION = new HttpException(500, "服务器错误");


}
