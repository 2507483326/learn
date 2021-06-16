import config.PropertyUtil;
import factory.CopyHandlerFactory;
import handler.AbstractFileHandler;
import handler.ChannelFileHandler;
import model.CopyModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author 李涛
 * @date : 2021/6/11 10:10
 */
public class Main {
    public static Logger logger = LogManager.getLogger(Main.class);

    public static void main (String [] args) throws IOException, IllegalAccessException, InstantiationException {
        Properties properties = PropertyUtil.getConfig("config.properties");
        CopyModel copyModel = (CopyModel) PropertyUtil.getPropByClass(properties, CopyModel.class);
        AbstractFileHandler abstractFileHandler = CopyHandlerFactory.getFileHandler(CopyHandlerFactory.CHANNEL);
        long startTime=System.currentTimeMillis();
        abstractFileHandler.copyAllFile(new File("E:\\test\\a"), new File("E:\\test\\b"), copyModel.getNewSuffix(), copyModel.getNewPrefix(), copyModel.getNewName(), copyModel.getNewFileType(), copyModel.getNeedCopyNames() == null ? null : Arrays.stream(copyModel.getNeedCopyNames().split(",")).collect(Collectors.toList()));
        long endTime=System.currentTimeMillis();
        logger.info("执行耗时" + (endTime - startTime) + "ms");
    }

}
