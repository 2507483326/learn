package com.epat.proxy.jdk;

/**
 * @author 李涛
 * @date : 2021/7/5 11:51
 */
public class Test {
	public static void main(String[] args) {
		System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		Calculator proxy = CalculatorProxy.getProxy(new MyCalculator());
		proxy.add(1, 1);
	}

}
