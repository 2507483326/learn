package com.epat.factory;

/**
 * @author 李涛
 * @date : 2021/7/10 13:42
 */
public class WindowsDialog extends Dialog{
    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}
