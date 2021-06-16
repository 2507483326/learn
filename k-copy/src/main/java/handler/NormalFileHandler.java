package handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.nio.ByteBuffer;

/**
 * @author 李涛
 * @date : 2021/6/16 13:16
 */
public class NormalFileHandler extends AbstractFileHandler{
    public static Logger logger = LogManager.getLogger(NormalFileHandler.class);

    protected static volatile NormalFileHandler instance;

    protected NormalFileHandler () {}

    public static NormalFileHandler getInstance () {
        if (instance == null) {
            synchronized (NormalFileHandler.class) {
                if (instance == null) {
                    instance = new NormalFileHandler();
                }
            }
        }
        return instance;
    }

    @Override
    protected void copyFile(File source, File target) {
        try (FileInputStream is = new FileInputStream(source);
             FileOutputStream os = new FileOutputStream(target);) {
            byte[] buffer = new byte[4096];
            while (is.read(buffer) != -1) {
                os.write(buffer);
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

}
