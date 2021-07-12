package com.epat.factory;

/**
 * @author 李涛
 * @date : 2021/7/10 13:39
 */
public class HtmlButton implements Button{
    @Override
    public void render() {
        System.out.println("<button>Test Button</button>");
        onClick();
    }

    @Override
    public void onClick() {
        System.out.println("Click! Button says - 'Hello World!'");
    }
}
