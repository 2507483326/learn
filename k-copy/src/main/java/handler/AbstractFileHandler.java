package handler;

import model.CopyModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 李涛
 * @date : 2021/6/16 13:04
 */
public abstract class AbstractFileHandler {
    public static Logger logger = LogManager.getLogger(AbstractFileHandler.class);

    protected AbstractFileHandler () {

    }

    /**
     * 判断是否为文件夹
     * @param file 目标
     * @return
     */
    public Boolean isDirectory (File file) {
        if (!file.exists()) {
            return false;
        }
        if (!file.isDirectory()) {
            return false;
        }
        return true;
    }

    /**
     * 获取所有子文件
     * @param file 目标
     * @return
     */
    public List<File> getChildFile(File file) {
        return getChildFile(file, true);
    }

    /**
     * 获取所有子文件
     * @param file 目标
     * @param isFile 是否是文件
     * @return
     */
    public List<File> getChildFile(File file, Boolean isFile) {
        if (!isDirectory(file)) {
            return null;
        }
        List<File> files = new LinkedList<>();
        for (File child : file.listFiles()) {
            if (isFile ? !child.isDirectory() : child.isDirectory()) {
                files.add(child);
            }
        }
        return files;
    }

    /**
     * 获取所有子目录
     * @param file 目标
     * @return
     */
    public List<File> getChildDirectory(File file) {
        return getChildFile(file, false);
    }

    protected abstract void copyFile (File source, File target);

    public void copyChildFile (File source, File target) {
        copyChildFile(source, target, null, null, null, null, null);
    }

    /**
     * 复制目录下所有子文件
     * @param source 来源
     * @param target 目标
     * @param newSuffix 新的后缀
     * @param newPrefix 新的前缀
     * @param newName 新的名称
     * @param newFileType 新的文件类型
     * @param needCopyNamesArray 是否可以复制数组
     */
    public void copyChildFile (File source, File target, String newSuffix, String newPrefix, String newName, String newFileType, List<String> needCopyNamesArray) {
        if (!source.isDirectory()) {
            throw new RuntimeException(source.getName() + "该路径不是文件夹");
        }

        List<File> childFiles = getChildFile(source);
        childFiles = childFiles.stream().filter(childFile -> {
            return isFileCanCopy(childFile, needCopyNamesArray);
        }).collect(Collectors.toList());
        if (childFiles.size() != 0) {
            logger.info("=====开始复制从" + source.getName() + " 到 " + target.getName() + "=====");
            logger.info("总文件数量为" + childFiles.size());
        }
        for (int i = 0; i < childFiles.size(); i++) {
            File file = childFiles.get(i);
            File outFile = new File(target.getPath() + File.separatorChar + getNewFileName(file.getName(), newSuffix, newPrefix, newName, newFileType, childFiles.size() > 1 ? (i + 1) : null));
            File outParentFile = new File(outFile.getParent());
            if (!outParentFile.exists()) {
                outParentFile.mkdirs();
            }
            copyFile(file, outFile);
            logger.info("文件 " + file.getName() +" 复制完成, " + (i + 1));
        }
    }

    /**
     * 复制所有文件
     * @param source 来源
     * @param target 目标
     * @param newSuffix 新的后缀
     * @param newPrefix 新的前缀
     * @param newName 新的名称
     * @param newFileType 新的文件类型
     * @param needCopyNamesArray 是否可以复制数组
     */
    public void copyAllFile (File source, File target, String newSuffix, String newPrefix, String newName, String newFileType, List<String> needCopyNamesArray) {
        if (!source.isDirectory()) {
            throw new RuntimeException(source.getName() + "该路径不是文件夹");
        }
        copyChildFile(source, target, newSuffix, newPrefix, newName, newFileType, needCopyNamesArray);
        for (File newSource : getChildDirectory(source)) {
            File nextTarget = new File(target.getPath() + File.separatorChar + newSource.getName());
            copyAllFile(newSource, nextTarget, newSuffix, newPrefix, newName, newFileType, needCopyNamesArray);
        }
    }

    public void copyAllFile (CopyModel copyModel) {
        copyAllFile(new File(copyModel.getSourcePath()), new File(copyModel.getTargetPath()), copyModel.getNewSuffix(), copyModel.getNewPrefix(), copyModel.getNewName(), copyModel.getNewFileType(), copyModel.getNeedCopyNames() == null ? null : Arrays.stream(copyModel.getNeedCopyNames().split(",")).collect(Collectors.toList()));
    }

    public void copyAllFile (File source, File target) {
        copyAllFile(source, target, null, null, null, null, null);
    }

    /**
     * 根据规则获取新的文件名称
     * @param fileName 老文件名称
     * @param newSuffix 新的后缀
     * @param newPrefix 新的前缀
     * @param newName 新的名称
     * @param newFileType 新的文件类型
     * @param count 计数标记
     * @return
     */
    private String getNewFileName (String fileName, String newSuffix, String newPrefix, String newName, String newFileType, Integer count) {
        int index = fileName.lastIndexOf(".");
        String fileType = fileName.substring(index);
        if (newFileType != null && !"".equals(newFileType.trim())) {
            fileType = newFileType;
        }
        if (newName != null && !"".equals(newName.trim())) {
            return newName + (count == null ? "" : count) + fileType;
        }
        String result = fileName.substring(0, index);
        if (newPrefix != null && !"".equals(newPrefix.trim())) {
            result = newPrefix + result;
        }
        if (newSuffix != null && !"".equals(newSuffix.trim())) {
            result = result + newSuffix;
        }
        return result + fileType;
    }

    /**
     * 根据数组内容判断文件是否可以复制
     * @param file
     * @param needCopyNamesArray
     * @return
     */
    public static Boolean isFileCanCopy (File file, List<String> needCopyNamesArray) {
        if (needCopyNamesArray == null) {
            return true;
        }
        for (String s : needCopyNamesArray) {
            if (file.getName().matches(s)) {
                return true;
            }
        }
        return false;
    }
}
