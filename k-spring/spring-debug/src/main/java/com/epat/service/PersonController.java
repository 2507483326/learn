package com.epat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 李涛
 * @date : 2021/7/6 14:32
 */
@Component
public class PersonController {

	@Autowired
	private PersonService personService;

}
