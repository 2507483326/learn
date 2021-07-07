package com.epat.aspect;

import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 李涛
 * @date : 2021/7/7 10:01
 */
public class AspectFactory {

    private List<Object> aspectList;

    private static List<AspectModel> aspectMethodList = null;

    public AspectFactory(List<Object> aspectList) {
        this.aspectList = aspectList;
        try {
            initMethod();
        } catch (Exception e) {
            throw new RuntimeException("初始化切面失败");
        }
    }

    public Object instantiate (Object bean) {
        BaseAspect baseAspect = new BaseAspect(aspectMethodList);
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(bean.getClass());
        enhancer.setCallbacks(new Callback[]{baseAspect, NoOp.INSTANCE});
        enhancer.setCallbackFilter(new CallbackFilter() {
            @Override
            public int accept(Method method) {
                if (method.getName().equals("toString")) {
                    return 1;
                }
                return 0;
            }
        });
        return enhancer.create();
    }

    public void initMethod () throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException {
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
            aspectMethodList.add(new AspectModel(method, clazz.newInstance()));
        }
    }

}
