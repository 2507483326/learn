package com.epat.observation;

import java.io.File;

/**
 * @author 李涛
 * @date : 2021/7/29 8:50
 */
public class LogOpenListener implements EventListener{

    private File log;

    public LogOpenListener(String fileName) {
        this.log = new File(fileName);
    }

    @Override
    public void update(String eventType, File file) {
        System.out.println("Save to log " + log + ": Someone has performed " + eventType + " operation with the following file: " + file.getName());
    }

}
