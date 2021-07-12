package com.epat.annotation.bean;

import java.lang.annotation.*;

/**
 * @author 李涛
 * @date : 2021/7/5 21:26
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Bean {

    String value() default "";

}
