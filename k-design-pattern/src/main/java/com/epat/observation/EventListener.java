package com.epat.observation;

import java.io.File;

/**
 * @author 李涛
 * @date : 2021/7/29 8:50
 */
public interface  EventListener {

    void update(String eventType, File file);

}
