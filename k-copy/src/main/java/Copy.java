import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 李涛
 * @date : 2021/6/11 10:10
 */
public class Copy {

    public static CopyConfig copyConfig;

    public static String [] needCopyNamesArray;

    public static Integer num = 1;

    /**
     * 复制文件到新的目录
     * @throws IOException
     * @throws IllegalAccessException
     */
    public static void copy () throws IOException, IllegalAccessException {
        num = 0;
        copyConfig = loadConfig();
        if (copyConfig == null) {
            throw new RuntimeException("加载配置文件失败");
        }
        List<File> result = getNeedCopyFiles();
        copyToNewPath(result);
    }

    /**
     * 获取需要复制的文件
     * @return
     */
    public static List<File> getNeedCopyFiles () {
        List<File> result = new ArrayList<>();
        File pathFile = new File(copyConfig.getOldPath());
        if (!pathFile.exists()) {
            throw new RuntimeException("该文件或目录不存在");
        }
        if (!pathFile.isDirectory()) {
            throw new RuntimeException("该路径不是文件夹");
        }
        File [] files = pathFile.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                continue;
            }
            if (!isFileCanCopy(file)) {
                continue;
            }
            result.add(file);
        }
        return result;
    };

    /**
     * 加载配置文件
     * @return
     */
    public static CopyConfig loadConfig () {
        try {
            File file = new File(Copy.class.getClassLoader().getResource("config.txt").getPath());
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Map<String, String> configMap = new HashMap<>();
            String s = null;
            while ((s = bufferedReader.readLine()) != null) {
                String [] r = s.split("=");
                if (r.length > 1) {
                    configMap.put(r[0], r[1]);
                }
            }
            CopyConfig copyConfig = new CopyConfig();
            for (Field declaredField : CopyConfig.class.getDeclaredFields()) {
                declaredField.setAccessible(true);
                String value = configMap.get(declaredField.getName());
                if (isNotEmpty(value)) {
                    declaredField.set(copyConfig, value);
                }
            }
            return copyConfig;
        } catch (Exception e) {
            System.out.println("加载配置文件失败");
        }
        return null;
    }

    /**
     * 判断文件是否可以复制
     * @param file
     * @return
     */
    public static Boolean isFileCanCopy (File file) {
        String needCopyNames = copyConfig.getNeedCopyNames();
        if (!isNotEmpty(needCopyNames)) {
            return true;
        }
        if (needCopyNamesArray == null) {
            needCopyNamesArray = needCopyNames.split(",");
        }
        for (String s : needCopyNamesArray) {
            if (file.getName().matches(s)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 复制文件到新的地址
     * @param needCopyFiles
     * @throws IOException
     */
    public static void copyToNewPath(List<File> needCopyFiles) throws IOException {
        File pathFile = new File(copyConfig.getNewPath());
        if (!pathFile.exists()) {
            throw new RuntimeException("该文件或目录不存在");
        }
        if (!pathFile.isDirectory()) {
            throw new RuntimeException("该路径不是文件夹");
        }
        System.out.println("开始复制, 总数量: " + needCopyFiles.size());
        int rate = 1;
        for (File needCopyFile : needCopyFiles) {
            File newFile = new File(pathFile.getPath() + "/" + getNewFileName(needCopyFile.getName()));
            newFile.createNewFile();
            FileInputStream fileInputStream = new FileInputStream(needCopyFile);
            FileOutputStream fileOutputStream = new FileOutputStream(newFile);
            byte [] b = new byte[1024];
            while (fileInputStream.read(b) != -1) {
                fileOutputStream.write(b);
            }
            fileInputStream.close();
            fileOutputStream.close();
            System.out.println("复制完成，当前进度：" + rate++);
        }
    }

    /**
     * 获取新的文件名称
     * @param fileName
     * @return
     */
    public static String getNewFileName (String fileName) {
        if (isNotEmpty(copyConfig.getNewName())) {
            int index = fileName.lastIndexOf(".");
            return copyConfig.getNewName() + (num++) + fileName.substring(index);
        }
        int index = fileName.lastIndexOf(".");
        String result = fileName.substring(0, index);
        String suffix = fileName.substring(index);
        if (isNotEmpty(copyConfig.getNewPrefix())) {
            result = copyConfig.getNewPrefix() + result;
        }
        if (isNotEmpty(copyConfig.getNewSuffix())) {
            result = result + copyConfig.getNewSuffix();
        }
        return result + suffix;
    }

    /**
     * 判断字符串是否为空
     * @param s
     * @return
     */
    public static Boolean isNotEmpty (String s) {
        return s != null && !"".equals(s.trim());
    }

}
