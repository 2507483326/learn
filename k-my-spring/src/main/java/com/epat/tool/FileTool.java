package com.epat.tool;

import com.epat.Application;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
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

    public static String readFile (File file) throws Exception {
        if (file == null) {
            return null;
        }
        if (!file.exists()) {
            throw new Exception(file.getName() + "文件不存在");
        }
        Charset charset = Charset.forName("UTF-8");
        String result = "";
        try (FileInputStream fileInputStream = new FileInputStream(file);
             FileChannel fileChannel = fileInputStream.getChannel()) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
            while (fileChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                CharBuffer charBuffer = charset.decode(byteBuffer);
                result += charBuffer.toString();
                byteBuffer.clear();
            }
            return result;
        }catch (Exception e) {
            throw new Exception("文件读取出错", e);
        }
    }
}
