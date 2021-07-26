package com.epat.compose;

import java.awt.*;

/**
 * @author 李涛
 * @date : 2021/7/14 17:28
 */
public interface Shape {

    int getX();
    int getY();
    int getWidth();
    int getHeight();
    void move(int x, int y);
    boolean isInsideBounds(int x, int y);
    void select();
    void unSelect();
    boolean isSelected();
    void paint(Graphics graphics);

}
