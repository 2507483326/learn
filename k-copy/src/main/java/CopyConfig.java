import java.util.List;

/**
 * @author 李涛
 * @date : 2021/6/11 10:10
 */
public class CopyConfig {

    /**
     * 复制之前文件夹路径
     */
    private String oldPath;

    /**
     * 复制之后文件夹路径
     */
    private String newPath;

    /**
     * 新的后缀
     */
    private String newSuffix;

    /**
     * 新的前缀
     */
    private String newPrefix;

    /**
     * 新的名称
     */
    private String newName;

    /**
     * 需要复制的文件
     */
    private String needCopyNames;


    public String getOldPath() {
        return oldPath;
    }

    public void setOldPath(String oldPath) {
        this.oldPath = oldPath;
    }

    public String getNewPath() {
        return newPath;
    }

    public void setNewPath(String newPath) {
        this.newPath = newPath;
    }

    public String getNewSuffix() {
        return newSuffix;
    }

    public void setNewSuffix(String newSuffix) {
        this.newSuffix = newSuffix;
    }

    public String getNewPrefix() {
        return newPrefix;
    }

    public void setNewPrefix(String newPrefix) {
        this.newPrefix = newPrefix;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public String getNeedCopyNames() {
        return needCopyNames;
    }

    public void setNeedCopyNames(String needCopyNames) {
        this.needCopyNames = needCopyNames;
    }

    @Override
    public String toString() {
        return "CopyConfig{" +
                "oldPath='" + oldPath + '\'' +
                ", newPath='" + newPath + '\'' +
                ", newSuffix='" + newSuffix + '\'' +
                ", newPrefix='" + newPrefix + '\'' +
                ", newName='" + newName + '\'' +
                ", needCopyNames='" + needCopyNames + '\'' +
                '}';
    }
}
