package com.epat.parse;

import com.epat.Application;
import com.epat.beanDefinition.AnnotationWrapper;
import com.epat.beanDefinition.FieldWrapper;
import com.epat.beanDefinition.MethodWrapper;
import jdk.internal.org.objectweb.asm.AnnotationVisitor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.lang.model.element.AnnotationValue;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 李涛
 * @date : 2021/7/9 10:51
 */
public class ClassParse {
    public static Logger logger =  LogManager.getLogger(ClassParse.class);

    public static ClassWrapper parse(Class clazz) {
        ClassWrapper classWrapper = new ClassWrapper();
        classWrapper.setClazz(clazz);
        classWrapper.setClassName(clazz.getName());
        classWrapper.getAnnotationWrappers().addAll(parseAnnotationWrapper(clazz.getDeclaredAnnotations()));
        classWrapper.getMethodWrappers().addAll(parseMethodWrapper(clazz.getDeclaredMethods()));
        classWrapper.getFieldWrappers().addAll(parseFieldWrapper(clazz.getDeclaredFields()));
        return classWrapper;
    }

    public static List<AnnotationWrapper> parseAnnotationWrapper (Annotation[] annotations)  {
        List<AnnotationWrapper> annotationWrapperList = new ArrayList<>();
        for (Annotation annotation : annotations) {
            AnnotationWrapper annotationWrapper = new AnnotationWrapper();
            annotationWrapper.setType(annotation.annotationType());
            annotationWrapper.setAnnotation(annotation);
            annotationWrapperList.add(annotationWrapper);
            InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);
            try {
                Field value = invocationHandler.getClass().getDeclaredField("memberValues");
                value.setAccessible(true);
                Map<String, Object> memberValues = (Map<String, Object>) value.get(invocationHandler);
                annotationWrapper.setValues(memberValues);
            } catch (Exception e) {
                logger.error("注解参数解析失败", e);
            }
        }
        return annotationWrapperList;
    }

    public static List<MethodWrapper> parseMethodWrapper (Method[] methods) {
        List<MethodWrapper> methodWrappers = new ArrayList<>();
        for (Method method : methods) {
            if (!method.getDeclaringClass().getName().equals(Object.class.getName())) {
                MethodWrapper methodWrapper = new MethodWrapper();
                methodWrapper.setMethod(method);
                methodWrapper.setName(method.getName());
                methodWrapper.setAnnotations(parseAnnotationWrapper(method.getDeclaredAnnotations()));
                methodWrappers.add(methodWrapper);
            }
        }
        return methodWrappers;
    }

    public static List<FieldWrapper> parseFieldWrapper (Field[] fields) {
        List<FieldWrapper> fieldWrappers = new ArrayList<>();
        for (Field field : fields) {
            FieldWrapper fieldWrapper = new FieldWrapper();
            fieldWrapper.setField(field);
            fieldWrapper.setName(field.getName());
            fieldWrapper.setType(field.getType());
            fieldWrapper.setAnnotations(parseAnnotationWrapper(field.getDeclaredAnnotations()));
            fieldWrappers.add(fieldWrapper);
        }
        return fieldWrappers;
    }
}
