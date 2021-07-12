package com.epat.factory;

/**
 * @author 李涛
 * @date : 2021/7/10 13:41
 */
public abstract class Dialog {

    public void renderWindow() {
        Button okButton = createButton();
        okButton.render();
    }

    /**
     * Subclasses will override this method in order to create specific button
     * objects.
     */
    public abstract Button createButton();

}
