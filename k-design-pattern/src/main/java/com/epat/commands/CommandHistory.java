package com.epat.commands;

import java.util.Stack;

/**
 * @author 李涛
 * @date : 2021/7/22 13:24
 */
public class CommandHistory {

    private Stack<Command> history = new Stack<>();

    public void push(Command c) {
        history.push(c);
    }

    public Command pop() {
        return history.pop();
    }

    public boolean isEmpty() { return history.isEmpty(); }

}
