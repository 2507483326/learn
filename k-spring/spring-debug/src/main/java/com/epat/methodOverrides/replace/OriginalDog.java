package com.epat.methodOverrides.replace;

/**
 * @author 李涛
 * @date : 2021/7/4 19:13
 */
public class OriginalDog {

	public void sayHello() {
		System.out.println("Hello,I am a black dog...");
	}

	public void sayHello(String name) {
		System.out.println("Hello,I am a black dog, my name is " + name);
	}

}
