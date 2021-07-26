package com.epat.commands;

/**
 * @author 李涛
 * @date : 2021/7/22 13:24
 */
public class CopyCommand extends Command{

    public CopyCommand(Editor editor) {
        super(editor);
    }

    @Override
    public boolean execute() {
        editor.clipboard = editor.textField.getSelectedText();
        return false;
    }

}
