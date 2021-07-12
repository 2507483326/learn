package com.epat.beanDefinition;

import lombok.Data;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 李涛
 * @date : 2021/7/5 12:07
 */
@Data
public class BeanDefinition {

    private String id;

    private Object clazz;

    private Boolean isSingle;

    private Boolean isFactoryBean = false;

    private List<Property> propertyList = new ArrayList<>();

    private Boolean isInstantiation = false;

    private Boolean isPrimary = false;

    private String factoryBeanId;

    private Method factoryBeanMethod;

    private List<Parameter> parameters;

    private List<AnnotationWrapper> annotations = new ArrayList<>();

}
