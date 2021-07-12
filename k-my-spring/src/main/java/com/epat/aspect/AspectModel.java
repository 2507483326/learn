package com.epat.aspect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;

/**
 * @author 李涛
 * @date : 2021/7/7 12:07
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AspectModel {

    private String name;

    private Method method;

    private Object instance;

    private String methodFilter;

    private String classNameFilter;

}
