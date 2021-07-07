package com.epat.annotation;

import java.lang.annotation.*;

/**
 * @author 李涛
 * @date : 2021/7/5 21:27
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Configuration {

    String value() default "";

}
