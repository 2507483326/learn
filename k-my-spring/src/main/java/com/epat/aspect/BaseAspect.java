package com.epat.aspect;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author 李涛
 * @date : 2021/7/7 10:19
 */
public class BaseAspect implements MethodInterceptor {

    private List<AspectModel> aspectModels;

    public BaseAspect(List<AspectModel> aspectModels) {
        this.aspectModels = aspectModels;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        ProceedingJoinPoint proceedingJoinPoint = new ProceedingJoinPoint(o, method, objects, methodProxy);
        return interceptMethod(0, o, proceedingJoinPoint);
    }

    public Object interceptMethod (Integer num, Object o, ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        if (num.intValue() < aspectModels.size() - 1) {
            AspectModel aspectModel = aspectModels.get(num);
            num++;
            Method method = aspectModel.getMethod();
            Method interceptMethod = BaseAspect.class.getMethod("interceptMethod", Integer.class, Object.class, ProceedingJoinPoint.class);
            ProceedingJoinPoint next = new ProceedingJoinPoint(this, interceptMethod, new Object[] {num, o, proceedingJoinPoint}, null);
            return method.invoke(aspectModel.getO(), next);
        }
        if (num.intValue() == aspectModels.size() - 1) {
            AspectModel aspectModel = aspectModels.get(num);
            Method method = aspectModel.getMethod();
            num++;
            return method.invoke(aspectModel.getO(), proceedingJoinPoint);
        }
        return null;
    }

}
