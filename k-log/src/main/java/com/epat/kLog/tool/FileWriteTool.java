package com.epat.kLog.tool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
/**
 * @author 李涛
 * @date : 2021/6/8 9:49
 */
public class FileWriteTool {

    public static void write (File file, String message) {
        try (Writer writer = new FileWriter(file, true))
        {
            writer.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
