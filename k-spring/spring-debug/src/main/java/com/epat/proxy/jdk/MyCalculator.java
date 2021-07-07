package com.epat.proxy.jdk;

/**
 * @author 李涛
 * @date : 2021/7/5 11:53
 */
public class MyCalculator implements Calculator{

	@Override
	public Integer add(Integer a, Integer b) {
		return a + b;
	}

}
