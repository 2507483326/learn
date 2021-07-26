package com.epat.trees;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 李涛
 * @date : 2021/7/15 20:19
 */
public class TreeFactory {

    static Map<String, TreeType> treeTypes = new HashMap<>();

    public static TreeType getTreeType(String name, Color color, String otherTreeData) {
        TreeType result = treeTypes.get(name);
        if (result == null) {
            result = new TreeType(name, color, otherTreeData);
            treeTypes.put(name, result);
        }
        return result;
    }

}
