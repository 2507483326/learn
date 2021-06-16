package factory;

import handler.AbstractFileHandler;
import handler.ChannelFileHandler;
import handler.NormalFileHandler;
import handler.ThreadFileHandler;

/**
 * @author 李涛
 * @date : 2021/6/16 14:18
 */
public class CopyHandlerFactory {

    // 普通
    public static Integer NORMAL = 1;
    // nio
    public static Integer CHANNEL = 2;
    // 多线程
    public static Integer THREAD = 3;


    public static AbstractFileHandler getFileHandler (Integer type) {
        if (type.intValue() == NORMAL.intValue()) {
            return NormalFileHandler.getInstance();
        }
        if (type.intValue() == CHANNEL.intValue()) {
            return ChannelFileHandler.getInstance();
        }
        if (type.intValue() == THREAD.intValue()) {
            return ThreadFileHandler.getInstance();
        }
        return null;
    }

}
