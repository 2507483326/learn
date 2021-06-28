package com.epat.selfPropertyEditor;

import com.epat.MyClassPathXmlApplicationContext;
import com.epat.selftag.User;
import org.springframework.context.ApplicationContext;

/**
 * @author 李涛
 * @date : 2021/6/23 11:56
 */
public class TestSelfPropertyEditor {

	public static void main (String [] args) {
		ApplicationContext applicationContext = new MyClassPathXmlApplicationContext("selfPropertyEditor.xml");
		Customer customer = applicationContext.getBean(Customer.class);
		System.out.println(customer);
	}

}
