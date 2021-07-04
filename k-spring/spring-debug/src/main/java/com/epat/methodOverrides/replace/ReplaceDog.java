package com.epat.methodOverrides.replace;

import org.springframework.beans.factory.support.MethodReplacer;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author 李涛
 * @date : 2021/7/4 19:13
 */
public class ReplaceDog implements MethodReplacer {

	@Override
	public Object reimplement(Object obj, Method method, Object[] args) throws Throwable {
		System.out.println("Hello, I am a white dog");
		Arrays.stream(args).forEach(str -> System.out.println("参数" + str));
		return obj;
	}

}
