package com.epat.procuration;

/**
 * @author 李涛
 * @date : 2021/7/15 20:28
 */
public class Video {

    public String id;
    public String title;
    public String data;

    Video(String id, String title) {
        this.id = id;
        this.title = title;
        this.data = "Random video.";
    }

}
