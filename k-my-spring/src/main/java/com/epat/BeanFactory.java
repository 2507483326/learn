package com.epat;

import com.epat.beanDefinition.BeanDefinition;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @author 李涛
 * @date : 2021/7/5 12:07
 */
public class BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap;

    private Map<String, Object> beanMap;

    private Map<String, Object> instantiationBeanMap;

    private List<Object> aspectList;

    public BeanFactory () {
        beanDefinitionMap = new HashMap<>();
        beanMap = new HashMap<>();
        instantiationBeanMap = new HashMap<>();
        aspectList = new ArrayList<>();
    }

    public void addBeanDefinition (BeanDefinition beanDefinition) {
        BeanDefinition r = beanDefinitionMap.put(beanDefinition.getId(), beanDefinition);
        if (r != null) {
            throw new RuntimeException("重复的key");
        }
    }

    public Set<String> getBeanDefinitionNames () {
        return beanDefinitionMap.keySet();
    }

    public BeanDefinition getBeanDefinition (String id) {
        return beanDefinitionMap.get(id);
    }

    public Object getBeanByBeanMap (String name) {
        return beanMap.get(name);
    }

    public Object getBeanByInstantiationBeanMap (String name) {
        return instantiationBeanMap.get(name);
    }

    public void addBeanToInstantiationBeanMap (String name, Object bean) {
        instantiationBeanMap.put(name, bean);
    }
    public void addBeanToBeanMap (String name, Object bean) {
        beanMap.put(name, bean);
    }
    public void removeToInstantiationBeanMap (String name) {
        instantiationBeanMap.remove(name);
    }

    public String findBeanNameByType (String type) throws ClassNotFoundException {
        Class typeClazz = Class.forName(type);
        for (BeanDefinition value : beanDefinitionMap.values()) {
            if (value.getClazz() instanceof String) {
                Class clazz = Class.forName((String) value.getClazz());
                value.setClazz(clazz);
            }
            if (typeClazz.isAssignableFrom((Class)value.getClazz()) || typeClazz == (Class) value.getClazz()) {
                return value.getId();
            }
        }
        return null;
    }

    public List<Object> getAspectList () {
        return this.aspectList;
    }
}
