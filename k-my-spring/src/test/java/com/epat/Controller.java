package com.epat;

import com.epat.annotation.Autowired;
import com.epat.annotation.Component;
import com.epat.service.SchoolService;

/**
 * @author 李涛
 * @date : 2021/7/6 19:48
 */
@Component
public class Controller {

    @Autowired
    private SchoolService schoolService;

    public void say () {
        System.out.println("Controller执行say方法1");
        schoolService.say();
        System.out.println("Controller执行say方法2");
    }

}
