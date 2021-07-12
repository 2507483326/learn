package com.epat.annotation.bean;

import java.lang.annotation.*;

/**
 * @author 李涛
 * @date : 2021/7/8 19:00
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Around {

    String value() default "";
}
