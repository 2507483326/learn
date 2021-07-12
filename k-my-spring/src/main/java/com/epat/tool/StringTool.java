package com.epat.tool;

/**
 * @author 李涛
 * @date : 2021/7/5 13:48
 */
public class StringTool {

    public static Boolean isEmpty (String text) {
        return text == null || "".equals(text);
    }

    public static String toLowCaseFirstChar (String str) {
        return  str.substring(0,1).toLowerCase().concat(str.substring(1));
    }

}
