package com.epat;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author 李涛
 * @date : 2021/6/17 11:04
 */
public class MyBeanFactoryPostProcess implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		BeanDefinition a = beanFactory.getBeanDefinition("a");
		System.out.println("设置beanDefinition");
	}
}
