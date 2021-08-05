package com.epat.visitor;

/**
 * @author 李涛
 * @date : 2021/7/31 9:28
 */
public interface  Shape {

    void move(int x, int y);
    void draw();
    String accept(Visitor visitor);

}
