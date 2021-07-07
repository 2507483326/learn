package com.epat.model;

import com.epat.annotation.Aspect;
import com.epat.aspect.ProceedingJoinPoint;
import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;


/**
 * @author 李涛
 * @date : 2021/7/6 22:09
 */
@Aspect
public class TestAspect {

    public Object around (ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("=================TestAspect调用前1====================");
        Object result = proceedingJoinPoint.proceed();
        System.out.println("=================TestAspect调用后2====================");
        return result;
    }

}
