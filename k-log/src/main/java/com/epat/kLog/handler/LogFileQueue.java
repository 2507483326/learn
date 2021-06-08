package com.epat.kLog.handler;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * @author 李涛
 * @date : 2021/6/8 9:49
 */
public class LogFileQueue {

    public static final int QUEUE_MAX_SIZE = 100;

    public static LinkedBlockingQueue<String> linkedBlockingQueue = null;

    public static ThreadPoolExecutor executor = null;

    public static volatile int LOG_NUM = 0;


    static {
        linkedBlockingQueue = new LinkedBlockingQueue<>(QUEUE_MAX_SIZE);

        executor = new ThreadPoolExecutor(100, 300, 200, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>(100));

    }


}
