package com.epat.tool;

import java.io.File;

/**
 * @author 李涛
 * @date : 2021/7/8 20:14
 */
public class ClazzTool {

    public static String getClassName (String fullClazzName) {
        if (fullClazzName == null) {
            return null;
        }
        int index = fullClazzName.lastIndexOf(".");
        return StringTool.toLowCaseFirstChar(fullClazzName.substring(index + 1));
    }
    public static String getFullClassName (String descriptor) {
        if (descriptor.startsWith("L")) {
            descriptor = descriptor.substring(1);
        }

        descriptor = descriptor.replace(File.separator, ".");
        if (descriptor.endsWith(";")){
            descriptor = descriptor.substring(0, descriptor.length() - 1);
        }
        return descriptor;
    }

    public static String getId (String id, String fullClazzName) {
        if (!StringTool.isEmpty(id)) {
            return id;
        }
        return getClassName(fullClazzName);
    }
}
