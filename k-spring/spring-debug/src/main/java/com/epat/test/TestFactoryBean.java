package com.epat.test;

import com.epat.A;
import org.springframework.cglib.core.DebuggingClassWriter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 李涛
 * @date : 2021/6/17 12:05
 */
public class TestFactoryBean {

	public static void main (String [] args) {
		System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "E:\\class");
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		// 这里可以通过&AFactoryBean 获取到工厂类
		// AFactoryBean aFactoryBean - applicationContext.getBean("&AFactoryBean");
		A a = (A) applicationContext.getBean("a");
		B b = (B) applicationContext.getBean("b");
		System.out.println(a);
	}

}
