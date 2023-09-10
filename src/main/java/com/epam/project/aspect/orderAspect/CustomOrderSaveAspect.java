package com.epam.project.aspect.orderAspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class CustomOrderSaveAspect {
    @Pointcut("@annotation(com.epam.project.aspect.annotation.OrderSaveAspectAnnotation)")
    public void orderSavePointCut() {
    }

    @Before("orderSavePointCut()")
    public void beforeAdvice(JoinPoint joinPoint) {
        log.info("Before save order method invoked: " + joinPoint.getSignature());
    }

    @After("orderSavePointCut()")
    public void afterAdvice(JoinPoint joinPoint) {
        log.info("After saved order method invoked: " + joinPoint.getSignature());
    }
}
