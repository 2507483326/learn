package com.epat.http.constant;

import lombok.Data;

/**
 * @author 李涛
 * @date : 2021/6/29 20:42
 */
public class HttpConstant {

    public static class Methods {

        public static String GET = "get";
        public static String POST = "post";
        public static String HEAD = "head";
        public static String PUT = "put";
        public static String DELETE = "delete";
        public static String OPTIONS = "options";
        public static String CONNECT = "connect";

    }

    @Data
    public static class State {
        private int code;

        private String message;

        public State(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public static State SUCCESS = new State(200, "OK");
    }

}
