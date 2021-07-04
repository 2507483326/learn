package com.epat.methodOverrides;

import com.epat.MyClassPathXmlApplicationContext;
import com.epat.methodOverrides.lookup.FruitPlate;
import com.epat.methodOverrides.replace.OriginalDog;
import org.springframework.context.ApplicationContext;

/**
 * @author 李涛
 * @date : 2021/7/4 11:37
 */
public class TestMethodOverrides {

	public static void main(String[] args) {
		// spring 中默认对象都是单列，会在1级缓存中
		ApplicationContext applicationContext = new MyClassPathXmlApplicationContext("methodOverrides.xml");
		FruitPlate fruitPlate = (FruitPlate)applicationContext.getBean("fruitPlate1");
		fruitPlate.getFruit();
		FruitPlate fruitPlate1 = (FruitPlate)applicationContext.getBean("fruitPlate2");
		fruitPlate1.getFruit();
		OriginalDog originalDog = (OriginalDog) applicationContext.getBean("originalDog");
		originalDog.sayHello("输出结果已经被替换了。。。");
	}
}
