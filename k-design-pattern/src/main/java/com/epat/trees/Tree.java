package com.epat.trees;

import java.awt.*;

/**
 * @author 李涛
 * @date : 2021/7/15 20:18
 */
public class Tree {

    private int x;
    private int y;
    private TreeType type;

    public Tree(int x, int y, TreeType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void draw(Graphics g) {
        type.draw(g, x, y);
    }

}
