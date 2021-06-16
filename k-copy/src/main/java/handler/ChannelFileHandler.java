package handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author 李涛
 * @date : 2021/6/16 13:21
 */
public class ChannelFileHandler extends AbstractFileHandler{
    public static Logger logger = LogManager.getLogger(ChannelFileHandler.class);

    protected static volatile ChannelFileHandler instance;

    protected ChannelFileHandler () {}

    public static ChannelFileHandler getInstance () {
        if (instance == null) {
            synchronized (ChannelFileHandler.class) {
                if (instance == null) {
                    instance = new ChannelFileHandler();
                }
            }
        }
        return instance;
    }

    @Override
    protected void copyFile(File source, File target) {
        try (FileInputStream is = new FileInputStream(source);
             FileOutputStream os = new FileOutputStream(target);
             FileChannel ic = is.getChannel();
             FileChannel oc = os.getChannel();) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(4096);
            while (ic.read(buffer) != -1) {
                buffer.flip();
                oc.write(buffer);
                buffer.clear();
            }
        } catch (Exception e) {
            logger.error(e);
        }
    }

}
