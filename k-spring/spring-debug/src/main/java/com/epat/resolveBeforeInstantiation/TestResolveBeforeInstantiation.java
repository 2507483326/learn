package com.epat.resolveBeforeInstantiation;

import com.epat.A;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 李涛
 * @date : 2021/6/17 12:05
 */
public class TestResolveBeforeInstantiation {

	public static void main (String [] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("resolveBeforeInstantiation.xml");
		BeforeInstantiation beforeInstantiation = (BeforeInstantiation) applicationContext.getBean("beforeInstantiation");
		beforeInstantiation.doSomeThing();
	}

}
