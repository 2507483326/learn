package com.epat.bean;

import com.epat.beanDefinition.BeanDefinition;
import com.epat.beanDefinition.Property;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 李涛
 * @date : 2021/7/9 14:58
 */
public class BeanProperty {


    public static void paddingValue (BeanFactory beanFactory, BeanDefinition beanDefinition, Object bean) throws Exception {
        if (beanDefinition.getPropertyList() == null || beanDefinition.getPropertyList().size() == 0) {
            return;
        }
        for (Property property : beanDefinition.getPropertyList()) {
            Method method = findMethod(property.getKey(), (Class) beanDefinition.getClazz());
            Object value = null;
            if (property.getRef() != null) {
                Class clazz = Class.forName(property.getType());
                value = BeanInstantiate.doCreateBean(beanFactory, clazz , property.getRef());
            } else {
                value = property.getValue();
            }
            if (method != null) {
                method.setAccessible(true);
                method.invoke(bean, value);
            } else {
                Field field = ((Class) beanDefinition.getClazz()).getDeclaredField(property.getKey());
                field.setAccessible(true);
                field.set(bean, value);
            }
        }
    }

    public static Method findMethod (String name, Class clazz) {
        List<Method> methods = new ArrayList<>();
        for (Method declaredMethod : clazz.getDeclaredMethods()) {
            if (declaredMethod.getName().startsWith("set")) {
                String baseName = declaredMethod.getName().substring(3).toLowerCase();
                if (baseName.equals(name)) {
                    methods.add(declaredMethod);
                }
            }
        }
        if (methods.size() > 0) {
            return methods.get(0);
        }
        return null;
    }

}
