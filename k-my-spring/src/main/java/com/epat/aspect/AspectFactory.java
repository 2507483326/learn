package com.epat.aspect;

import com.epat.bean.BeanFactory;
import com.epat.beanDefinition.BeanDefinition;
import com.epat.tool.StringTool;
import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 李涛
 * @date : 2021/7/7 10:01
 */
public class AspectFactory {


    public static Object proxy (BeanFactory beanFactory, BeanDefinition beanDefinition, Object bean) {
        if (beanDefinition.getIsFactoryBean()) {
            return bean;
        }
        List<AspectModel> aspectModels = initAspectList((Class) beanDefinition.getClazz(), beanFactory.getAspectList());
        if (aspectModels == null || aspectModels.size() == 0) {
            return bean;
        }
        BaseAspect baseAspect = new BaseAspect(aspectModels);
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(bean.getClass());
        enhancer.setCallbacks(new Callback[]{baseAspect, NoOp.INSTANCE});
        enhancer.setCallbackFilter(new CallbackFilter() {
            @Override
            public int accept(Method method) {
                if (method.getDeclaringClass().getName().equals(Object.class.getName())) {
                    return 1;
                }
                return 0;
            }
        });
        Object newBean = enhancer.create();
        return newBean;
    }

    public static List<AspectModel> initAspectList (Class clazz, List<AspectModel> aspectModels) {
        List<AspectModel> filterList = aspectModels.stream().filter(i -> {
            if (!StringTool.isEmpty(i.getClassNameFilter())) {
                if (clazz.getName().matches(i.getClassNameFilter())) {
                    return true;
                } else {
                    return false;
                }
            }
            return true;
        }).collect(Collectors.toList());
        return filterList;
    }

/*    public void initMethod () throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (aspectMethodList != null) {
            return;
        }
        aspectMethodList = new ArrayList<>();
        for (Object o : aspectList) {
            Class clazz = null;
            if (o instanceof String) {
                clazz = Class.forName(o.toString());
            } else {
                clazz = (Class) o;
            }
            Method method = clazz.getMethod("around", ProceedingJoinPoint.class);
            // aspectMethodList.add(new AspectModel(method, clazz.newInstance()));
        }
    }*/

}
