package com.epat.bridging;

/**
 * @author 李涛
 * @date : 2021/7/13 19:22
 */
public interface Device {

    boolean isEnabled();

    void enable();

    void disable();

    int getVolume();

    void setVolume(int percent);

    int getChannel();

    void setChannel(int channel);

    void printStatus();

}
