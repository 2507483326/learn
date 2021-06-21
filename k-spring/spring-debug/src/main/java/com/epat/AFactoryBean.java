package com.epat;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author 李涛
 * @date : 2021/6/17 12:06
 */
public class AFactoryBean implements FactoryBean<A> {
	@Override
	public A getObject() throws Exception {
		return new A();
	}

	@Override
	public Class<?> getObjectType() {
		return A.class;
	}
}
