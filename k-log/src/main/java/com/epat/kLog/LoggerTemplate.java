package com.epat.kLog;

import com.epat.kLog.constant.LogConstant;
/**
 * @author 李涛
 * @date : 2021/6/8 9:49
 */
public class LoggerTemplate {

    public void info(Object message) {
        handlerLog(LogConstant.INFO, message);
    }

    public void warn(Object message) {
        handlerLog(LogConstant.WARN, message);
    }

    public void debug(Object message) {
        handlerLog(LogConstant.DEBUG, message);
    }

    public void error(Object message) {
        handlerLog(LogConstant.ERROR, message);
    }

    protected void handlerLog (Integer level, Object message) {
        throw new RuntimeException("No handler Log Function");
    }

}
