package com.security.controller.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;

//ToDO need uncomment
//@Aspect
//@Component
public class TimeAspect {
    @Around("execution(* com.security.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws  Throwable{
        System.out.println("time aspect start");
        Object[] args = pjp.getArgs();
        Arrays.stream(args).forEach( a -> System.out.println("arg is : " + a));
        long start = new Date().getTime();
        Object object = pjp.proceed();
        System.out.println("time aspect: time used - " + (new Date().getTime() - start));
        System.out.println("time aspect end");

        return  object;
    }
}
