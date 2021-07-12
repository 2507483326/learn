package com.epat.test;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 李涛
 * @date : 2021/7/9 9:52
 */
@Configuration
public class TestConfiguration {

	@Bean
	public A getA() {
		return new A();
	}

	@Bean
	public B getB(A a) {
		B b = new B();
		b.setA(a);
		return b;
	}

}
