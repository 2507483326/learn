package com.epat.observation;

import java.io.File;

/**
 * @author 李涛
 * @date : 2021/7/29 8:50
 */
public class EmailNotificationListener implements EventListener {

    private String email;

    public EmailNotificationListener(String email) {
        this.email = email;
    }

    @Override
    public void update(String eventType, File file) {
        System.out.println("Email to " + email + ": Someone has performed " + eventType + " operation with the following file: " + file.getName());
    }

}
