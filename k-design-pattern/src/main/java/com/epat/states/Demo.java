package com.epat.states;

/**
 * @author 李涛
 * @date : 2021/7/31 9:05
 */
public class Demo {
    public static void main(String[] args) {
        Player player = new Player();
        UI ui = new UI(player);
        ui.init();
    }

}
