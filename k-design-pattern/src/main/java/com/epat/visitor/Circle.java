package com.epat.visitor;

/**
 * @author 李涛
 * @date : 2021/7/31 9:29
 */
public class Circle extends Dot{

    private int radius;

    public Circle(int id, int x, int y, int radius) {
        super(id, x, y);
        this.radius = radius;
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visitCircle(this);
    }

    public int getRadius() {
        return radius;
    }

}
