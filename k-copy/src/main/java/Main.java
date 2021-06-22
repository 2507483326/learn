import config.CopyConfig;
import factory.CopyHandlerFactory;
import handler.AbstractFileHandler;
import model.CopyModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author 李涛
 * @date : 2021/6/11 10:10
 */
public class Main {
    public static Logger logger = LogManager.getLogger(Main.class);

    public static void main (String [] args)  {
        CopyModel copyModel = CopyConfig.loadCopyModel();
        AbstractFileHandler abstractFileHandler = CopyHandlerFactory.getFileHandler(CopyHandlerFactory.CHANNEL);
        long startTime=System.currentTimeMillis();
        abstractFileHandler.copyAllFile(copyModel);
        long endTime=System.currentTimeMillis();
        logger.info("执行耗时" + (endTime - startTime) + "ms");
    }

}
