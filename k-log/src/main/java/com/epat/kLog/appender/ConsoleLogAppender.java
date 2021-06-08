package com.epat.kLog.appender;

/**
 * @author 李涛
 * @date : 2021/6/8 9:49
 */
public class ConsoleLogAppender extends LogAppender {

    @Override
    public void out(String message) {
        System.out.println(message);
    }

}
