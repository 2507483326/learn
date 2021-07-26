package com.epat.bridging;

/**
 * @author 李涛
 * @date : 2021/7/13 19:24
 */
public class AdvancedRemote extends BasicRemote{
    public AdvancedRemote(Device device) {
        super.device = device;
    }

    public void mute() {
        System.out.println("Remote: mute");
        device.setVolume(0);
    }
}
