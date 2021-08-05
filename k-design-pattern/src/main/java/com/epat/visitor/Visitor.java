package com.epat.visitor;

/**
 * @author 李涛
 * @date : 2021/7/31 9:30
 */
public interface  Visitor {

    String visitDot(Dot dot);

    String visitCircle(Circle circle);

    String visitRectangle(Rectangle rectangle);

    String visitCompoundGraphic(CompoundShape cg);

}
