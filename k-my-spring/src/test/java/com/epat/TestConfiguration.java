package com.epat;

import com.epat.A;
import com.epat.B;
import com.epat.annotation.bean.Bean;
import com.epat.annotation.type.Configuration;

/**
 * @author 李涛
 * @date : 2021/7/9 22:31
 */
@Configuration
public class TestConfiguration {

    @Bean
    public A a () {
        return  new A();
    }

    @Bean
    public B b (A a) {
        return new B(a);
    }

}
