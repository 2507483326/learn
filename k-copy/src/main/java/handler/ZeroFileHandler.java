package handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @author 李涛
 * @date : 2021/7/26 13:01
 */
public class ZeroFileHandler extends AbstractFileHandler{

    public static Logger logger = LogManager.getLogger(ChannelFileHandler.class);

    protected static volatile ZeroFileHandler instance;

    protected ZeroFileHandler () {}

    public static ZeroFileHandler getInstance () {
        if (instance == null) {
            synchronized (ZeroFileHandler.class) {
                if (instance == null) {
                    instance = new ZeroFileHandler();
                }
            }
        }
        return instance;
    }


    @Override
    protected void copyFile(File source, File target) {
        try {
            if (!target.exists()) {
                target.createNewFile();
            }
        } catch (Exception e) {
            logger.error(e);
        }
        try (FileChannel is = new RandomAccessFile(source, "r").getChannel();
            FileChannel os = new RandomAccessFile(target, "rw").getChannel()) {
            is.transferTo(0, source.length(), os);
        } catch (Exception e) {
            logger.error(e);
        }
    }

}
