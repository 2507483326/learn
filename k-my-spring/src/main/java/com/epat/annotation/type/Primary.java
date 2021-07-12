package com.epat.annotation.type;

import java.lang.annotation.*;

/**
 * @author 李涛
 * @date : 2021/7/8 19:01
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Primary {
}
