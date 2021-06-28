package com.epat.selftag;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author 李涛
 * @date : 2021/6/23 11:32
 */
public class UserNamespaceHandler extends NamespaceHandlerSupport {
	@Override
	public void init() {
		registerBeanDefinitionParser("user", new UserBeanDefinitionParser());
	}
}
