package com.epat.kLog.converter;

import java.text.SimpleDateFormat;
/**
 * @author 李涛
 * @date : 2021/6/8 9:49
 */
public class DateConverter{

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static String transform(Object event) {
        return BracketsConverter.transform(simpleDateFormat.format(event));
    }

}
