package com.epat.aspect;

import lombok.Data;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author 李涛
 * @date : 2021/7/6 22:18
 */
@Data
public class ProceedingJoinPoint {

    private Object o;
    private Method method;
    private Object[] args;
    private MethodProxy methodProxy;

    public ProceedingJoinPoint(Object o, Method method, Object[] args, MethodProxy methodProxy) {
        this.o = o;
        this.method = method;
        this.args = args;
        this.methodProxy = methodProxy;
    }

    public Object proceed () throws Throwable {
        if (methodProxy == null) {
            return method.invoke(o, args);
        }
        return methodProxy.invokeSuper(o, args);
    }

}
