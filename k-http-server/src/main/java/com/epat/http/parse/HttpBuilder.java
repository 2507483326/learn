package com.epat.http.parse;

import lombok.Data;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author 李涛
 * @date : 2021/6/29 22:46
 */
@Data
public class HttpBuilder {

    private HttpEntry httpEntry = new HttpEntry();

    public HttpEntry build () {
        return httpEntry;
    }

    public HttpBuilder header (String header) {
        String [] headerList = header.split("\r\n");
        LinkedHashMap<String, String> headerMap = (LinkedHashMap<String, String>)Arrays.stream(headerList).skip(1).filter(item -> item.contains(":"))
                .collect(Collectors.toMap(h -> h.split(":")[0], h -> h.split(":")[1], (u,v) -> { throw new IllegalStateException(String.format("Duplicate key %s", u)); }, LinkedHashMap::new));
        String [] httpLineArray = headerList[0].split(" ");
        HttpHeader httpHeader = new HttpHeader();
        httpHeader.setAllHeaders(headerMap);
        httpHeader.setContentLength(headerMap.get("Content-Length") == null ? 0 : Integer.parseInt(headerMap.get("Content-Length")));
        httpHeader.setMethods(httpLineArray[0]);
        httpHeader.setUrl(httpLineArray[1]);
        httpHeader.setVersion(httpLineArray[2]);
        httpEntry.setHttpHeader(httpHeader);
        return this;
    }

    public HttpBuilder body (String body) {
        httpEntry.setBody(body);
        return this;
    }
}
