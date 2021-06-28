package com.epat.selftag;

import com.epat.A;
import com.epat.MyClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;

/**
 * @author 李涛
 * @date : 2021/6/23 11:56
 */
public class TestSelfTag {

	public static void main (String [] args) {
		ApplicationContext applicationContext = new MyClassPathXmlApplicationContext("applicationContext.xml");
		User user = applicationContext.getBean(User.class);
		System.out.println(user);
	}

}
