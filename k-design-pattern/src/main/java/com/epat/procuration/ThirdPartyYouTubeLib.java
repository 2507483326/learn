package com.epat.procuration;

import java.util.HashMap;

/**
 * @author 李涛
 * @date : 2021/7/15 20:28
 */
public interface ThirdPartyYouTubeLib {
    HashMap<String, Video> popularVideos();

    Video getVideo(String videoId);
}
