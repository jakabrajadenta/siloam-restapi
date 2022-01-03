package com.siloam.restapi.configuration.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopExecution {

    @Pointcut("execution(* getMessage())")
    public void getMessageMethod(){}

    @Before("getMessageMethod()")
    public void logMessageBefore(JoinPoint joinPoint){
        System.out.println("Before getMessage");
    }

    @After("getMessageMethod()")
    public void logMessageAfter(JoinPoint joinPoint){
        System.out.println("After getMessage");
    }

    @Around("getMessageMethod()")
    public Object logMessageAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Before Around getMessage");
        Object message = joinPoint.proceed();
        System.out.println("After Around getMessage");
        return message;
    }

}
