package com.epat.kLog;

import com.epat.kLog.appender.ConsoleLogAppender;
import com.epat.kLog.appender.FileLogAppender;
import com.epat.kLog.appender.LogAppender;

import java.util.*;
/**
 * @author 李涛
 * @date : 2021/6/8 9:49
 */
public class Logger extends LoggerTemplate {

    private static LinkedHashMap<String, LogAppender> logHandlerHashMap = new LinkedHashMap<>();

    static {
        ConsoleLogAppender consoleLogHandler = new ConsoleLogAppender();
        FileLogAppender fileLogHandler = new FileLogAppender();
        logHandlerHashMap.put(ConsoleLogAppender.class.getName(), consoleLogHandler);
        logHandlerHashMap.put(FileLogAppender.class.getName(), fileLogHandler);
    }

    private Class clazz;


    public Logger (Class clazz) {
        this.clazz = clazz;
    }

    @Override
    protected void handlerLog(Integer level, Object message) {
        Thread thread = Thread.currentThread();
        logHandlerHashMap.forEach((key, logAppender) -> {
            logAppender.log(thread, clazz, level, message);
        });
    }


}
