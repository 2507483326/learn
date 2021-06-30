package com.epat.http.parse;

import lombok.Data;

import java.util.Map;

/**
 * @author 李涛
 * @date : 2021/6/29 22:47
 */
@Data
public class HttpHeader {

    private String url;

    private String methods;

    private String version;

    private Integer contentLength;

    private Map<String, String> allHeaders;

}
