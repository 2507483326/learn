package com.epat.absoluteFactory;

/**
 * @author 李涛
 * @date : 2021/7/11 11:23
 */
public class MacOSFactory implements GUIFactory{
    @Override
    public Button createButton() {
        return new MacOSButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacOSCheckbox();
    }
}
