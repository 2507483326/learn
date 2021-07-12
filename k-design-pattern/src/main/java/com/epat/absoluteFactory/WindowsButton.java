package com.epat.absoluteFactory;

/**
 * @author 李涛
 * @date : 2021/7/11 11:22
 */
public class WindowsButton implements Button {
    @Override
    public void paint() {
        System.out.println("You have created WindowsButton.");
    }
}
