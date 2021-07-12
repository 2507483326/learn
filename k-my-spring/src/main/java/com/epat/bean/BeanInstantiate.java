package com.epat.bean;

import com.epat.Application;
import com.epat.aspect.AspectFactory;
import com.epat.beanDefinition.BeanDefinition;
import com.epat.beanDefinition.Property;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 李涛
 * @date : 2021/7/9 14:11
 */
public class BeanInstantiate {

    public static Logger logger =  LogManager.getLogger(BeanInstantiate.class);

    public static void createBean (BeanFactory beanFactory) throws Exception {

        for (String name : beanFactory.getBeanDefinitionNames()) {
            beanFactory.doCreateBean(name);
        }
    }

    private static void init (BeanFactory beanFactory) {

    }

    public static Object doCreateBean (BeanFactory beanFactory, Class type, String id) throws Exception {
        Object bean = doCreateBean(beanFactory, id);
        if (bean != null) {
            return bean;
        }
        String name = beanFactory.findBeanByType(type);
        bean = doCreateBean(beanFactory, name);
        return bean;
    }

    public static Object doCreateBean (BeanFactory beanFactory, String name) throws Exception {
        Object bean = getBeanByCache(beanFactory, name);
        if (bean != null) {
            return bean;
        }
        BeanWrapper beanWrapper = new BeanWrapper();
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition(name);
        if (beanDefinition == null) {
            return null;
        }
        beanWrapper.setIsInstantiate(true);
        // beanFactory.addBeanToInstantiationBeanMap(name, beanWrapper);
        if (beanDefinition.getClazz() instanceof String) {
            Class clazz = Class.forName((String) beanDefinition.getClazz());
            beanDefinition.setClazz(clazz);
        }
        bean = instantiate(beanFactory, beanDefinition);
        bean = AspectFactory.proxy(beanFactory, beanDefinition, bean);
        beanWrapper.setBean(bean);
        beanFactory.addBeanWithInstantiationMap(name, beanWrapper);
        BeanProperty.paddingValue(beanFactory, beanDefinition, bean);
        beanWrapper.setIsInstantiate(false);
        beanFactory.removeBeanWithInstantiationMap(name);
        beanFactory.addBean(name, beanWrapper);
        return bean;
    }

    private static Object getBeanByCache (BeanFactory beanFactory, String name) {
        BeanWrapper beanWrapper = null;
        beanWrapper = beanFactory.getBeanWithMap(name);
        if (beanWrapper != null) {
            return beanWrapper.getBean();
        }
        beanWrapper = beanFactory.getBeanWithInstantiationMap(name);
        if (beanWrapper != null && beanWrapper.getIsInstantiate()) {
            return beanWrapper.getBean();
        }
        return null;
    }

    private static Object instantiate (BeanFactory beanFactory, BeanDefinition beanDefinition) throws Exception {
        if (!beanDefinition.getIsFactoryBean()) {
            Class clazz = (Class) beanDefinition.getClazz();
            Object bean = clazz.newInstance();
            return bean;
        } else {
            Method method = beanDefinition.getFactoryBeanMethod();
            List<Object> params = new ArrayList<>();
            for (Parameter parameter : beanDefinition.getParameters()) {
                Object result = beanFactory.getBean(parameter.getType(), parameter.getName());
                params.add(result);
            }
            Object instance = beanFactory.getBean(beanDefinition.getFactoryBeanId());
            return method.invoke(instance, params.toArray());
        }
    }

}
