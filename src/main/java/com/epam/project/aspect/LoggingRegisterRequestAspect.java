package com.epam.project.aspect;

import com.epam.project.model.dto.RegistrationResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class LoggingRegisterRequestAspect {
  //  @Pointcut("execution(* com.epam.project.controller.AuthenticationController.*(..))")
    @Pointcut("@annotation(com.epam.project.aspect.annotation.CustomAspectAnnotation)")
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

//    @AfterReturning(value = "registerPointCut()", returning = "response")
//    public void afterReturning(JoinPoint joinPoint, RegistrationResponse response) {
//        log.info("After returning advice: " + response);
//    }
//    @AfterThrowing(value = "registerPointCut()", throwing = "exception")
//    public void afterThrowing(Exception exception){
//       log.info("After Throwing advice:: " + exception);
//    }

//    @Around("registerPointCut()")
//    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
//        Object returnValue = null;
//        try {
//            log.info("Before advice method invoked" );
//           returnValue= proceedingJoinPoint.proceed();
//            log.info("After advice method invoked" );
//        } catch (Throwable e) {
//            log.info("After throwing: ");
//        }
//        log.info("After Finally advice method invoked");
//        return returnValue;
//    }
}
