package com.epat.parse;

import com.epat.beanDefinition.AnnotationWrapper;
import com.epat.beanDefinition.FieldWrapper;
import com.epat.beanDefinition.MethodWrapper;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李涛
 * @date : 2021/7/9 11:02
 */
@Data
public class ClassWrapper {


    private String className;

    private Class clazz;

    private List<AnnotationWrapper> annotationWrappers = new ArrayList<>();

    private List<MethodWrapper> methodWrappers = new ArrayList<>();

    private List<FieldWrapper> fieldWrappers = new ArrayList<>();

}
