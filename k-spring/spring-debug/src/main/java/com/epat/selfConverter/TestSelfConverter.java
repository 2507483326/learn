package com.epat.selfConverter;

import com.epat.MyClassPathXmlApplicationContext;
import com.epat.selfPropertyEditor.Customer;
import org.springframework.context.ApplicationContext;

/**
 * @author 李涛
 * @date : 2021/6/23 11:56
 */
public class TestSelfConverter {

	public static void main (String [] args) {
		ApplicationContext applicationContext = new MyClassPathXmlApplicationContext("selfConverter.xml");
		Customer customer = applicationContext.getBean(Customer.class);
		System.out.println(customer);
	}

}
