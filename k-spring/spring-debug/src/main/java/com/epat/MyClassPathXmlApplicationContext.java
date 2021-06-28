package com.epat;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 自定义ApplicationContext,扩展initPropertySources方法
 * @author 李涛
 * @date : 2021/6/22 11:07
 */
public class MyClassPathXmlApplicationContext extends ClassPathXmlApplicationContext {


	public MyClassPathXmlApplicationContext (String configLocation) {
		super(new String[] {configLocation}, true, null);
	}

	@Override
	public void initPropertySources () {
		setAllowBeanDefinitionOverriding(true);
		System.out.println("自定义资源");
	}


}
