package com.epat.bean;

import com.epat.aspect.AspectModel;
import com.epat.beanDefinition.BeanDefinition;
import lombok.Data;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 李涛
 * @date : 2021/7/5 12:07
 */

public class BeanFactory {


    private static volatile  BeanFactory instance = null;

    private BeanBucket beanBucket = new BeanBucket();

    private BeanFactory () {}

    public static BeanFactory getInstance() {
        if (instance == null) {
            synchronized (BeanFactory.class) {
                if (instance == null) {
                    // 如果没有volatile会出现代码重排，导致instance为默认值，被返回回去
                    instance = new BeanFactory();
                }
            }
        }
        return instance;
    }

    public void addBeanDefinition (BeanDefinition beanDefinition) {
        beanBucket.getBeanDefinitionMap().put(beanDefinition.getId(), beanDefinition);
    }

    public void addBeanDefinition(List<BeanDefinition> beanDefinitions) {
        for (BeanDefinition beanDefinition : beanDefinitions) {
            addBeanDefinition(beanDefinition);
        }
    }

    public void addBeanWithInstantiationMap (String name, BeanWrapper beanWrapper) {
        beanBucket.getInstantiationBeanMap().put(name, beanWrapper);
    }

    public void addBean (String name, BeanWrapper beanWrapper) {
        beanBucket.getBeanMap().put(name, beanWrapper);
    }

    public void removeBeanWithInstantiationMap (String name) {
        beanBucket.getInstantiationBeanMap().remove(name);
    }
    public BeanDefinition getBeanDefinition (String name) {
        return beanBucket.getBeanDefinitionMap().get(name);
    }

    public List<String> getBeanDefinitionNames () {
        return beanBucket.getBeanDefinitionMap().keySet().stream().collect(Collectors.toList());
    }

    public void addAspect (AspectModel aspectModel) {
        beanBucket.getAspectList().add(aspectModel);
    }

    public Object getBean (String name) throws Exception {
        return BeanInstantiate.doCreateBean(this, name);
    }

    public Object getBean (Class type, String name) throws Exception {
        return BeanInstantiate.doCreateBean(this, type, name);
    }

    public Object doCreateBean (String name) throws Exception {
        return BeanInstantiate.doCreateBean(this, name);
    }

    public BeanWrapper getBeanWithMap (String name) {
        return beanBucket.getBeanMap().get(name);
    }

    public BeanWrapper getBeanWithInstantiationMap (String name) {
        return beanBucket.getInstantiationBeanMap().get(name);
    }

    public void addBeanToInstantiationBeanMap (String name, BeanWrapper beanWrapper) {
        beanBucket.getInstantiationBeanMap().put(name, beanWrapper);
    }

    public List<AspectModel> getAspectList () {
        return beanBucket.getAspectList();
    }

    public String findBeanByType (Class clazz) throws Exception {
        List<BeanDefinition> beanDefinitionList = new ArrayList<>();
        for (BeanDefinition value : beanBucket.getBeanDefinitionMap().values()) {
            if (value.getClazz() instanceof String) {
                value.setClazz(Class.forName((String) value.getClazz()));
            }
            if (clazz.isAssignableFrom((Class) value.getClazz())) {
                beanDefinitionList.add(value);
            }
            if (clazz == (Class) value.getClazz()) {
                beanDefinitionList.add(value);
            }
        }
        BeanDefinition beanDefinition = beanDefinitionList.stream().sorted((a, b) -> {
            if (a.getIsPrimary()) {
                return 1;
            }
            return 0;
        }).findFirst().get();
        return beanDefinition.getId();
    }
}
