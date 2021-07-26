package factory;

import handler.*;

/**
 * @author 李涛
 * @date : 2021/6/16 14:18
 */
public class CopyHandlerFactory {


    public static class CopyMode {

        // 普通
        public static Integer NORMAL = 1;
        // nio
        public static Integer CHANNEL = 2;
        // 多线程
        public static Integer THREAD = 3;
        // 零拷贝
        public static Integer ZERO_COPY = 4;
    }


    public static AbstractFileHandler getFileHandler (Integer type) {
        if (type.intValue() == CopyMode.NORMAL.intValue()) {
            return NormalFileHandler.getInstance();
        }
        if (type.intValue() == CopyMode.CHANNEL.intValue()) {
            return ChannelFileHandler.getInstance();
        }
        if (type.intValue() == CopyMode.THREAD.intValue()) {
            return ThreadFileHandler.getInstance();
        }
        if (type.intValue() == CopyMode.ZERO_COPY.intValue()) {
            return ZeroFileHandler.getInstance();
        }
        return null;
    }

}
