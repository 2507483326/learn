package com.epat.beanDefinition.loader.annotation;

import com.epat.annotation.bean.Around;
import com.epat.annotation.bean.Aspect;
import com.epat.annotation.bean.Autowired;
import com.epat.annotation.bean.Bean;
import com.epat.annotation.type.Component;
import com.epat.annotation.type.Configuration;
import com.epat.annotation.type.Primary;
import com.epat.annotation.type.Service;
import com.epat.aspect.AspectModel;
import com.epat.bean.BeanFactory;
import com.epat.beanDefinition.*;
import com.epat.parse.ClassWrapper;
import com.epat.tool.ClazzTool;
import com.epat.tool.StringTool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 李涛
 * @date : 2021/7/9 11:26
 */
public class BeanDefinitionAnnotationLoader {

    public static void load (BeanFactory beanFactory, List<ClassWrapper> classWrapperList) throws Exception {
        HashMap<String, BeanDefinition> beanDefinitionHashMap = new HashMap<>();
        for (ClassWrapper classWrapper : classWrapperList) {
            load(beanFactory, beanDefinitionHashMap, classWrapper);
        }
        beanFactory.addBeanDefinition(beanDefinitionHashMap.values().stream().collect(Collectors.toList()));
    }

    private static void load(BeanFactory beanFactory, HashMap<String, BeanDefinition> beanDefinitionHashMap, ClassWrapper classWrapper) throws Exception {
        for (AnnotationWrapper annotationWrapper : classWrapper.getAnnotationWrappers()) {
            if (annotationWrapper.getType().getName().equals(Service.class.getName()) || annotationWrapper.getType().getName().equals(Component.class.getName())) {
                addNormalBean(beanDefinitionHashMap, classWrapper, annotationWrapper);
                break;
            }
            if (annotationWrapper.getType().getName().equals(Aspect.class.getName())) {
                addAspect(beanFactory, classWrapper, annotationWrapper);
            }
            if (annotationWrapper.getType().getName().equals(Configuration.class.getName())) {
                addConfiguration(beanDefinitionHashMap, classWrapper, annotationWrapper);
            }
        }
    }

    private static BeanDefinition addNormalBean(HashMap<String, BeanDefinition> beanDefinitionHashMap, ClassWrapper classWrapper, AnnotationWrapper annotationWrapper) {
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setId(getName(classWrapper.getClassName(), annotationWrapper));
        if (beanDefinitionHashMap.get(beanDefinition.getId()) != null) {
            return null;
        }
        beanDefinition.setClazz(classWrapper.getClazz());
        beanDefinition.setPropertyList(getPropertyList(classWrapper));
        beanDefinition.setIsPrimary(getIsPrimary(classWrapper));
        beanDefinitionHashMap.put(beanDefinition.getId(), beanDefinition);
        return beanDefinition;
    }

    private static String getName (String customName, AnnotationWrapper annotationWrapper) {
        String name = (String) annotationWrapper.getValues().get("name");
        if (!StringTool.isEmpty(name)) {
            return name;
        }
        return ClazzTool.getClassName(customName);
    }


    private static List<Property> getPropertyList (ClassWrapper classWrapper) {
        List<Property> properties = new ArrayList<>();
        for (FieldWrapper fieldWrapper : classWrapper.getFieldWrappers()) {
            Property property = new Property();
            Boolean isNeedAddProperty = false;
            for (AnnotationWrapper annotation : fieldWrapper.getAnnotations()) {
                if (annotation.getType().getName().equals(Autowired.class.getName())) {
                    property.setRef(getName(fieldWrapper.getName(), annotation));
                    property.setKey(fieldWrapper.getName());
                    property.setType(fieldWrapper.getType().getName());
                    isNeedAddProperty = true;
                }
            }
            if (isNeedAddProperty) {
                properties.add(property);
            }
        }
        return properties;
    }

    private static Boolean getIsPrimary(ClassWrapper classWrapper) {
        Boolean isPrimary = false;
        for (AnnotationWrapper annotationWrapper : classWrapper.getAnnotationWrappers()) {
            if (annotationWrapper.getType().getName().equals(Primary.class.getName())) {
                isPrimary = true;
            }
        }
        return isPrimary;
    }

    private static void addAspect(BeanFactory beanFactory, ClassWrapper classWrapper, AnnotationWrapper annotationWrapper) throws Exception {
        AspectModel aspectModel = new AspectModel();
        aspectModel.setName(ClazzTool.getClassName(classWrapper.getClassName()));
        String classNameFilter = (String) annotationWrapper.getValues().get("name");
        aspectModel.setClassNameFilter(classNameFilter);
        aspectModel.setInstance(classWrapper.getClazz().newInstance());
        for (MethodWrapper methodWrapper : classWrapper.getMethodWrappers()) {
            for (AnnotationWrapper annotation : methodWrapper.getAnnotations()) {
                if (annotation.getType().getName().equals(Around.class.getName())) {
                    aspectModel.setMethod(methodWrapper.getMethod());
                    String methodFilter = (String) annotation.getValues().get("name");
                    aspectModel.setMethodFilter(methodFilter);
                }
            }
        }
        beanFactory.addAspect(aspectModel);
    }

    public static void addConfiguration (HashMap<String, BeanDefinition> beanDefinitionHashMap, ClassWrapper classWrapper, AnnotationWrapper annotationWrapper) {
        BeanDefinition configBeanDefinition = addNormalBean(beanDefinitionHashMap, classWrapper, annotationWrapper);
        for (MethodWrapper methodWrapper : classWrapper.getMethodWrappers()) {
            for (AnnotationWrapper annotation : methodWrapper.getAnnotations()) {
                if (annotation.getType().getName().equals(Bean.class.getName())) {
                    BeanDefinition beanDefinition = new BeanDefinition();
                    beanDefinition.setClazz(methodWrapper.getMethod().getReturnType());
                    beanDefinition.setId(getName(methodWrapper.getMethod().getName(), annotation));
                    beanDefinition.setIsFactoryBean(true);
                    beanDefinition.setFactoryBeanId(configBeanDefinition.getId());
                    beanDefinition.setFactoryBeanMethod(methodWrapper.getMethod());
                    beanDefinition.setParameters(Arrays.stream(methodWrapper.getMethod().getParameters()).collect(Collectors.toList()));
                    beanDefinitionHashMap.put(beanDefinition.getId(), beanDefinition);
                }
            }
        }
    }

}
