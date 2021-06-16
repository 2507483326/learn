package handler;

import java.io.*;

/**
 * @author 李涛
 * @date : 2021/6/16 13:24
 */
public class ThreadFileHandler extends AbstractFileHandler{

    private Integer threadCount = 5;


    protected static volatile ThreadFileHandler instance;

    protected ThreadFileHandler () {}

    public static ThreadFileHandler getInstance () {
        if (instance == null) {
            synchronized (ThreadFileHandler.class) {
                if (instance == null) {
                    instance = new ThreadFileHandler();
                }
            }
        }
        return instance;
    }

    @Override
    protected void copyFile(File source, File target) {
        long workLoad = source.length() / threadCount;
        for (Integer i = 0; i < threadCount; i++) {
            ThreadFileRunnable threadFileRunnable = new ThreadFileRunnable(source, target, i * workLoad, workLoad);
            new Thread(threadFileRunnable, "copy-thread" + i).start();
        }
    }

    private class ThreadFileRunnable implements Runnable {

        private File source;

        private File target;

        private long skipLen;

        private long workLoad;

        private final int IO_UNIT = 1024;

        public ThreadFileRunnable (File source, File target, long skipLen, long workLoad) {
            this.source = source;
            this.target = target;
            this.skipLen = skipLen;
            this.workLoad = workLoad;
        }

        @Override
        public void run() {
            try (FileInputStream is = new FileInputStream(this.source);
                 BufferedInputStream bis = new BufferedInputStream(is);
                 RandomAccessFile rof = new RandomAccessFile(this.target, "rw");) {
                bis.skip(this.skipLen);
                rof.seek(this.skipLen);
                byte[] bytes = new byte[IO_UNIT];
                long io_num = this.workLoad / IO_UNIT + 1;
                if (this.workLoad % IO_UNIT == 0) {
                    io_num--;
                }
                int count = bis.read(bytes);
                while (io_num != 0) {
                    rof.write(bytes,0,count);
                    count = bis.read(bytes,0,count);//重新计算count的值
                    io_num--;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
