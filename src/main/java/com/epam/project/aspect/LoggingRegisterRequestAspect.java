package com.epam.project.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LoggingRegisterRequestAspect {
    @Pointcut("execution(* com.epam.project.controller.AuthenticationController.*(..))")
    public void registerPointCut() {
    }

    @Before("registerPointCut()")
    public void beforeAdvice(JoinPoint joinPoint) {
        log.info("Before method invoked: " + joinPoint.getSignature());
    }

    @After("registerPointCut()")
    public void afterAdvice(JoinPoint joinPoint) {
        log.info("After method invoked: " + joinPoint.getSignature());
    }
}
