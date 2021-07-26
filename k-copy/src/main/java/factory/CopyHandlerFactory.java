package factory;

import handler.*;

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
    // 零拷贝
    public static Integer ZERO = 4;


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
        if (type.intValue() == ZERO.intValue()) {
            return ZeroFileHandler.getInstance();
        }
        return null;
    }

}
