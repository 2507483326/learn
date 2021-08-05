package com.epat.memorial;

/**
 * @author 李涛
 * @date : 2021/7/29 8:26
 */
public class Memento {

    private String backup;
    private Editor editor;

    public Memento(Editor editor) {
        this.editor = editor;
        this.backup = editor.backup();
    }

    public void restore() {
        editor.restore(backup);
    }

}
