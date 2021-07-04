package com.epat.resolveBeforeInstantiation;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author 李涛
 * @date : 2021/7/4 19:25
 */
public class MyMethodInterceptor implements MethodInterceptor {

	@Override
	public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
		System.out.println("目标方法执行之前");
		Object result = methodProxy.invokeSuper(o, objects);
		System.out.println("目标方法执行之后");
		return result;
	}

}
