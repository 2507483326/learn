package com.epat.factory;

/**
 * @author 李涛
 * @date : 2021/7/10 13:42
 */
public class Demo {

    private static Dialog dialog;

    public static void main(String[] args) {
        // 工厂模式提供一种功能的抽象，在运行时来决定使用功能的实现
        // 就像运输功能一样，既可以是火车也可以是轮船，可以在运行时来指定
        configure();
        runBusinessLogic();
    }

    static void configure() {
        if (System.getProperty("os.name").equals("Windows 10")) {
            dialog = new WindowsDialog();
        } else {
            dialog = new HtmlDialog();
        }
    }

    static void runBusinessLogic() {
        dialog.renderWindow();
    }

}
