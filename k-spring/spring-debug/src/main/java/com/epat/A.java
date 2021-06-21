package com.epat;

import lombok.Data;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author 李涛
 * @date : 2021/6/17 10:52
 */
@Data
public class A implements ApplicationContextAware {

	private String name;

	private ApplicationContext applicationContext;

	public void init () {
		System.out.println("初始化方法");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
