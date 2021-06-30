package com.epat.http.parse;

import lombok.Data;

/**
 * @author 李涛
 * @date : 2021/6/29 20:40
 */
@Data
public class HttpEntry {

    private HttpHeader httpHeader;

    private String body;

}
