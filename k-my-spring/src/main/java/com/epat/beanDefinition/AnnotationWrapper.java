package com.epat.beanDefinition;

import lombok.Data;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 李涛
 * @date : 2021/7/8 19:09
 */
@Data
public class AnnotationWrapper {

    private Class type;

    private Annotation annotation;

    private Map<String, Object> values;

}
