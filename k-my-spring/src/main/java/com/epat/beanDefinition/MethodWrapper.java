package com.epat.beanDefinition;

import com.epat.beanDefinition.AnnotationWrapper;
import lombok.Data;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 李涛
 * @date : 2021/7/8 22:07
 */
@Data
public class MethodWrapper {

    private String name;

    private Method method;

    private List<AnnotationWrapper> annotations = new ArrayList<>();

}
