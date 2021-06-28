package com.epat.selfBeanDefinitionsRegistryPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;

/**
 * @author 李涛
 * @date : 2021/6/28 9:57
 */
public class MyBeanDefinitionsRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("MyBeanDefinitionsRegistryPostProcessor执行postProcessBeanFactory");

	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		System.out.println("MyBeanDefinitionsRegistryPostProcessor执行postProcessBeanDefinitionRegistry");
		// 注册一个BeanDefinition 第一种方法
		// registry.registerBeanDefinition("teacher", new RootBeanDefinition(Teacher.class));
		// 注册一个BeanDefinition 第二种方法
		// BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(Teacher.class);
		// beanDefinitionBuilder.addPropertyValue("name", "张老师");
		// registry.registerBeanDefinition("teacher",beanDefinitionBuilder.getBeanDefinition());
		// 注册一个BeanDefinition新增BeanDefinitionRegistryPostProcessor
		registry.registerBeanDefinition("teacher", new RootBeanDefinition(AddBeanDefinitionsRegistryPostPorcessor.class));
	}
}
