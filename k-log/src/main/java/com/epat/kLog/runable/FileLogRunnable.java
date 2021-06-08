package com.epat.kLog.runable;

import com.epat.kLog.handler.FileLogHandler;
import com.epat.kLog.tool.FileWriteTool;

import java.io.File;
import java.util.concurrent.locks.ReentrantLock;
/**
 * @author 李涛
 * @date : 2021/6/8 9:49
 */
public class FileLogRunnable implements Runnable{


    private FileLogHandler fileLogHandler;

    public FileLogRunnable(FileLogHandler fileLogHandler) {
        this.fileLogHandler = fileLogHandler;
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
                FileWriteTool.write(new File("e://" + this.fileLogHandler.getFileName()), outMessage.toString());
            }
        }catch (Exception e) {
            System.out.println(e);
        }
    }


}
