package com.epat.iterators;

/**
 * @author 李涛
 * @date : 2021/7/22 19:42
 */
public interface  ProfileIterator {
    boolean hasNext();

    Profile getNext();

    void reset();
}
