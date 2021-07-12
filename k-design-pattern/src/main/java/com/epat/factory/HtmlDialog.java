package com.epat.factory;

/**
 * @author 李涛
 * @date : 2021/7/10 13:41
 */
public class HtmlDialog extends Dialog{
    @Override
    public Button createButton() {
        return new HtmlButton();
    }
}
