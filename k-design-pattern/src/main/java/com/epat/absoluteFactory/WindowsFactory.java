package com.epat.absoluteFactory;

/**
 * @author 李涛
 * @date : 2021/7/11 11:24
 */
public class WindowsFactory implements GUIFactory{
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}
