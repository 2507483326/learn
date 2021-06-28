package com.epat.selfBeanDefinitionsRegistryPostProcessor;

import com.epat.MyClassPathXmlApplicationContext;
import com.epat.selftag.User;
import org.springframework.context.ApplicationContext;

/**
 * @author 李涛
 * @date : 2021/6/23 11:56
 */
public class TestSelfBeanDefinitionsRegistryPostProcessor {

	public static void main (String [] args) {
		ApplicationContext applicationContext = new MyClassPathXmlApplicationContext("selfBeanDefinitionsRegistryPostProcessor.xml");

	}

}
