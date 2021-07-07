package com.epat.proxy.jdk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author 李涛
 * @date : 2021/7/5 11:53
 */
@ComponentScan
public interface Calculator {

	public Integer add (Integer a, Integer b);

}
