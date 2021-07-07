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

    public static String getPackageClass (String descriptor) {
        if (descriptor.startsWith("L")) {
            descriptor = descriptor.substring(1);
        }

        descriptor = descriptor.replace("/", ".");
        if (descriptor.endsWith(";")){
            descriptor = descriptor.substring(0, descriptor.length() - 1);
        }
        return descriptor;
    }

    public static String getClass (String descriptor) {
        String packageClass = getPackageClass(descriptor);
        int index = packageClass.lastIndexOf(".");
        return packageClass.substring(index + 1);
    }

    public static String getClassName (String descriptor) {
        String packageClass = getPackageClass(descriptor);
        int index = packageClass.lastIndexOf(".");
        return toLowCaseFirstChar(packageClass.substring(index + 1));
    }

}
