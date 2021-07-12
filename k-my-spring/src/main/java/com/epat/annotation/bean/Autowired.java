package com.epat.annotation.bean;

import java.lang.annotation.*;

/**
 * @author 李涛
 * @date : 2021/7/5 21:29
 */
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {

    String value() default "";

}
