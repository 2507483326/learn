package com.epat.http.web;

import lombok.Data;

/**
 * @author 李涛
 * @date : 2021/6/30 10:14
 */
@Data
public class Resource {

    private String type;

    private String data;

    public Resource(String type, String data) {
        this.type = type;
        this.data = data;
    }
}
