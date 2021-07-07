package com.epat.tool;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 李涛
 * @date : 2021/7/5 22:39
 */
public class FileTool {

    public static List<File> getChildFile (File file, FileFilter fileFilter) {
        List<File> list = new ArrayList<>();
        if (!file.exists()) {
            return list;
        }
        if (file.isDirectory()) {
            for (File child : file.listFiles(fileFilter)) {
                if (!child.isDirectory()) {
                    list.add(child);
                } else {
                    list.addAll(getChildFile(child, fileFilter));
                }
            }
        }
        return list;
    }

}
