package com.epat.annotation.type;

import java.lang.annotation.*;

/**
 * @author 李涛
 * @date : 2021/7/5 21:30
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

    String value() default "";

}
