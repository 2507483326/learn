package com.epat.iterators;

/**
 * @author 李涛
 * @date : 2021/7/22 19:44
 */
public interface  SocialNetwork {

    ProfileIterator createFriendsIterator(String profileEmail);

    ProfileIterator createCoworkersIterator(String profileEmail);

}
