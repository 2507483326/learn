package com.epat.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author 李涛
 * @date : 2021/7/5 11:52
 */
public class CalculatorProxy {

	public static Calculator getProxy(final Calculator calculator) {
		ClassLoader loader = calculator.getClass().getClassLoader();
		Class<?> [] interfaces = calculator.getClass().getInterfaces();
		InvocationHandler h = new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("执行方法前");
				Object result = method.invoke(calculator, args);
				System.out.println("执行方法后");
				return result;
			}
		};
		Object proxy = Proxy.newProxyInstance(loader, interfaces, h);
		return (Calculator) proxy;
	}

}
