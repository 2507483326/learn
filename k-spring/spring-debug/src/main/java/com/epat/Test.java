package com.epat;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 李涛
 * @date : 2021/6/17 10:44
 */
public class Test {

	public static void main (String [] args) {
		ApplicationContext applicationContext = new MyClassPathXmlApplicationContext("applicationContext.xml");
	}
}
