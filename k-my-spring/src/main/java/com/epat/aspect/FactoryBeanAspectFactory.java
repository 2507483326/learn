package com.epat.aspect;

import com.epat.bean.BeanFactory;
import com.epat.beanDefinition.BeanDefinition;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Method;
import java.util.List;

/**
 * @author 李涛
 * @date : 2021/7/9 18:08
 */
public class FactoryBeanAspectFactory {

    public static Object proxy (BeanFactory beanFactory, Object bean) {
        FactoryBeanAspect factoryBeanAspect = new FactoryBeanAspect(beanFactory);
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(bean.getClass());
        enhancer.setCallbacks(new Callback[]{factoryBeanAspect, NoOp.INSTANCE});
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

}
