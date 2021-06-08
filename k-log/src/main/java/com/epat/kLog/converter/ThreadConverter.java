package com.epat.kLog.converter;

import com.epat.kLog.constant.LogConstant;
/**
 * @author 李涛
 * @date : 2021/6/8 9:49
 */
public class ThreadConverter {

    public static String transform(Object event) {
        Thread thread = (Thread) event;
        return BracketsConverter.transform(thread.getName());
    }

}
