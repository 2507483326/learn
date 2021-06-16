package config;

import model.CopyModel;
import tool.PropertyTool;

import java.io.File;
import java.util.Properties;

/**
 * @author 李涛
 * @date : 2021/6/16 17:49
 */
public class CopyConfig {

    public static final String name = "config.properties";

    public static CopyModel loadCopyModel () {
        Properties properties = PropertyTool.getConfig("config.properties");
        if (properties == null) {
            throw new RuntimeException("配置文件不存在");
        }
        CopyModel copyModel = (CopyModel) PropertyTool.getPropByClass(properties, CopyModel.class);
        if (copyModel.getSourcePath() == null) {
            throw new RuntimeException("请配置源路径");
        }
        if (copyModel.getTargetPath() == null) {
            throw new RuntimeException("请配置目标路径");
        }
        File sourceFile = new File(copyModel.getSourcePath());
        File targetFile = new File(copyModel.getTargetPath());
        if (!sourceFile.exists()) {
            throw new RuntimeException("源路径不存在，请重新配置! 路径：" + sourceFile.getPath());
        }
        if (!sourceFile.isDirectory()) {
            throw new RuntimeException("源路径不是文件夹，请重新配置!");
        }
        return copyModel;
    }

}
