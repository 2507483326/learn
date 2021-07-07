package com.epat.model;

import com.epat.annotation.Aspect;
import com.epat.aspect.ProceedingJoinPoint;


/**
 * @author 李涛
 * @date : 2021/7/6 22:09
 */
@Aspect
public class TestAspect2 {

    public Object around (ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("=================TestAspect2调用前1====================");
        Object result = proceedingJoinPoint.proceed();
        System.out.println("=================TestAspect2调用后2====================");
        return result;
    }

}
