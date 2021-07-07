package com.epat.annotation;

import java.lang.annotation.*;

/**
 * @author 李涛
 * @date : 2021/7/5 21:33
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface ComponentScan {

    String value() default "";

}
