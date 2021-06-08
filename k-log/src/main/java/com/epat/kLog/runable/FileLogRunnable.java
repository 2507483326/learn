package com.epat.kLog.runable;

import com.epat.kLog.handler.FileLogHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
/**
 * @author 李涛
 * @date : 2021/6/8 9:49
 */
public class FileLogRunnable implements Runnable{


    private FileLogHandler fileLogHandler;

    private File file;

    private Writer writer;

    public FileLogRunnable(FileLogHandler fileLogHandler) {
        this.fileLogHandler = fileLogHandler;
        this.file = new File("./" + this.fileLogHandler.getFileName());
        try {
            this.writer = new FileWriter(file, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true){
                StringBuffer outMessage = new StringBuffer();
                for (int i = 0; i < 20; i++) {
                    String message = this.fileLogHandler.poll();
                    if (message != null) {
                        outMessage.append(message);
                        outMessage.append("\n");
                    }
                }
                writer.write(outMessage.toString());
                writer.flush();
            }
        }catch (Exception e) {
            System.out.println(e);
        }
    }


}
