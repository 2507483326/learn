package model;

import lombok.Data;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 李涛
 * @date : 2021/6/11 10:10
 */
@Data
public class CopyModel {

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

    /**
     * 新的文件类型
     */
    private String newFileType;

}
