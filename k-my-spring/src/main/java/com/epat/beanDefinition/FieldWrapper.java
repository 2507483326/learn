package com.epat.beanDefinition;

import lombok.Data;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 李涛
 * @date : 2021/7/9 11:39
 */
@Data
public class FieldWrapper {

    private String name;

    private Class type;

    private Field field;

    private List<AnnotationWrapper> annotations = new ArrayList<>();
}
