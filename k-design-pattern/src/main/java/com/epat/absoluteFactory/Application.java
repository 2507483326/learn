package com.epat.absoluteFactory;

/**
 * @author 李涛
 * @date : 2021/7/11 11:24
 */
public class Application {

    private Button button;
    private Checkbox checkbox;

    public Application(GUIFactory factory) {
        this.button = factory.createButton();
        this.checkbox = factory.createCheckbox();
    }

    public void paint() {
        button.paint();
        checkbox.paint();
    }

}
