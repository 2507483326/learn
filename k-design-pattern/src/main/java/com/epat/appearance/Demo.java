package com.epat.appearance;

import java.io.File;

/**
 * @author 李涛
 * @date : 2021/7/15 20:07
 */
public class Demo {

    public static void main(String[] args) {
        VideoConversionFacade converter = new VideoConversionFacade();
        File mp4Video = converter.convertVideo("youtubevideo.ogg", "mp4");
    }

}
