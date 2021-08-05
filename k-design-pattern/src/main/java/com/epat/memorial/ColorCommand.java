package com.epat.memorial;

import java.awt.*;

/**
 * @author 李涛
 * @date : 2021/7/29 8:26
 */
public class ColorCommand implements Command{

    private Editor editor;
    private Color color;

    public ColorCommand(Editor editor, Color color) {
        this.editor = editor;
        this.color = color;
    }

    @Override
    public String getName() {
        return "Colorize: " + color.toString();
    }

    @Override
    public void execute() {
        for (Shape child : editor.getShapes().getSelected()) {
            child.setColor(color);
        }
    }

}
