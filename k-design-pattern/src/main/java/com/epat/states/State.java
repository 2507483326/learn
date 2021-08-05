package com.epat.states;

/**
 * @author 李涛
 * @date : 2021/7/31 9:03
 */
public abstract  class State {

    Player player;

    /**
     * Context passes itself through the state constructor. This may help a
     * state to fetch some useful context data if needed.
     */
    State(Player player) {
        this.player = player;
    }

    public abstract String onLock();
    public abstract String onPlay();
    public abstract String onNext();
    public abstract String onPrevious();


}
