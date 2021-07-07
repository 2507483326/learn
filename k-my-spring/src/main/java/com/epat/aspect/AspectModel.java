package com.epat.aspect;

import java.lang.reflect.Method;

/**
 * @author 李涛
 * @date : 2021/7/7 12:07
 */
public class AspectModel {

    private Method method;

    private Object o;

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getO() {
        return o;
    }

    public void setO(Object o) {
        this.o = o;
    }

    public AspectModel(Method method, Object o) {
        this.method = method;
        this.o = o;
    }
}
