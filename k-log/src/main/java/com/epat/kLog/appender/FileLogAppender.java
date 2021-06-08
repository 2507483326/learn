package com.epat.kLog.appender;

import com.epat.kLog.converter.*;
import com.epat.kLog.handler.FileLogHandler;
import java.util.Date;
/**
 * @author 李涛
 * @date : 2021/6/8 9:49
 */
public class FileLogAppender extends LogAppender {


    private final FileLogHandler fileLogHandler = new FileLogHandler("log.txt");

    @Override
    public void out(String message) {
        fileLogHandler.push(message);
    }

    @Override
    protected String messageHandler (Thread thread, Class clazz, Integer level, Object message) {
        return DateConverter.transform(new Date()) + " " + BracketsConverter.transform(clazz.getClass().getName())  + LevelConverter.transform(level) + ThreadConverter.transform(thread)
                +       " >>> " + message.toString();
    }
}
