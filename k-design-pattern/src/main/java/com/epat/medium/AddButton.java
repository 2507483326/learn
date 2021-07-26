package com.epat.medium;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author 李涛
 * @date : 2021/7/23 9:16
 */
public class AddButton extends JButton implements Component{

    private Mediator mediator;

    public AddButton() {
        super("Add");
    }

    @Override
    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    @Override
    protected void fireActionPerformed(ActionEvent actionEvent) {
        mediator.addNewNote(new Note());
    }

    @Override
    public String getName() {
        return "AddButton";
    }

}
