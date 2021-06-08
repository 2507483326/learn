package com.epat.kLog.converter;

import com.epat.kLog.constant.LogConstant;
/**
 * @author 李涛
 * @date : 2021/6/8 9:49
 */
public class LevelConverter {

    public static String transform(Object event) {
        String result = "";
        int level = (int) event;
        if (LogConstant.ALL == level) {
            result = "ALL";
        }
        if (LogConstant.DEBUG == level) {
            result = "DEBUG";
        }
        if (LogConstant.ERROR == level) {
            result = "ERROR";
        }
        if (LogConstant.INFO == level) {
            result =  "INFO";
        }
        if (LogConstant.WARN == level) {
            result =  "WARN";
        }
        return BracketsConverter.transform(result);
    }

}
