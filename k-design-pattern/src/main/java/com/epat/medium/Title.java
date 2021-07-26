package com.epat.medium;

import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * @author 李涛
 * @date : 2021/7/23 9:17
 */
public class Title  extends JTextField implements Component {

    private Mediator mediator;

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    protected void processComponentKeyEvent(KeyEvent keyEvent) {
        mediator.markNote();
    }

    @Override
    public String getName() {
        return "Title";
    }

}
