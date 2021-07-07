package com.epat.service;

import com.epat.annotation.Autowired;
import com.epat.annotation.Service;
import com.epat.model.School;

/**
 * @author 李涛
 * @date : 2021/7/6 19:48
 */
@Service
public class SchoolServiceImpl implements SchoolService{

    public SchoolServiceImpl() {
        System.out.println("SchoolServiceImpl 构造器");
    }

    @Autowired
    School school;

    @Override
    public void say() {
        System.out.println("SchoolServiceImpl执行say方法1");
        System.out.println("say hello");
        System.out.println("SchoolServiceImpl执行say方法2");
    }

}
