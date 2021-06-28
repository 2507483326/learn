package com.epat.selfBeanDefinitionsRegistryPostProcessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * @author 李涛
 * @date : 2021/6/28 10:03
 */
public class AddBeanDefinitionsRegistryPostPorcessor implements BeanDefinitionRegistryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("AddBeanDefinitionsRegistryPostPorcessor执行postProcessBeanFactory");
	}

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		System.out.println("AddBeanDefinitionsRegistryPostPorcessor执行postProcessBeanDefinitionRegistry");
	}
}
