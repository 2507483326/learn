package com.epat.aspect;

import com.epat.annotation.bean.Bean;
import com.epat.bean.BeanFactory;
import com.epat.beanDefinition.BeanDefinition;
import com.epat.tool.StringTool;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 李涛
 * @date : 2021/7/9 18:09
 */
public class FactoryBeanAspect implements MethodInterceptor {


    private BeanFactory beanFactory;

    public FactoryBeanAspect(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        ProceedingJoinPoint proceedingJoinPoint = new ProceedingJoinPoint(o, method, objects, methodProxy);
        Bean annotation = method.getAnnotation(Bean.class);
        if (annotation == null) {
            return proceedingJoinPoint.proceed();
        }
        String name = method.getName();
        if (!StringTool.isEmpty(annotation.value())) {
            name = annotation.value();
        }
        /*List<Object> params = new ArrayList<>();
        for (Parameter parameter : beanDefinition.getParameters()) {
            Object result = beanFactory.getBean(parameter.getType(), parameter.getName());
            params.add(result);
        }
        Object instance = beanFactory.getBean(beanDefinition.getFactoryBeanId());*/
        return beanFactory.getBean(name);
    }


}
