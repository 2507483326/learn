package com.epat.kLog.handler;

import com.epat.kLog.runable.FileLogRunnable;

import java.io.File;
import java.util.concurrent.LinkedBlockingQueue;
/**
 * @author 李涛
 * @date : 2021/6/8 9:49
 */
public class FileLogHandler {


    private LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();

    private String fileName;

    public FileLogHandler (String fileName) {
        this.fileName = fileName;
        Thread fileLogRunnable = new Thread(new FileLogRunnable(this));
        fileLogRunnable.setDaemon(true);
        fileLogRunnable.start();
    }

    public void push (String message) {
        try {
            linkedBlockingQueue.put(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String poll () {
        String message = linkedBlockingQueue.poll();
        return message;
    }

    public int size () {
        return linkedBlockingQueue.size();
    }

    public LinkedBlockingQueue<String> queue () {
        return this.linkedBlockingQueue;
    }

    public String getFileName () {
        return this.fileName;
    }

}
