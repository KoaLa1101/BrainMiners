package ru.itlab.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SignAspect {
    @Before("execution(* ru.itlab.controllers.SignController.showSignUpForm(..))")
    public void logBefore(JoinPoint joinPoint){
        System.out.println("logs BEFORE by " + joinPoint.getSignature().getName());
        System.out.println("Registration start");
    }

    @AfterReturning(pointcut = "execution(* ru.itlab.controllers.SignController.addUser(..))",
    returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result){
        System.out.println("logs AFTER_RETURNING by " + joinPoint.getSignature().getName());
        System.out.println(joinPoint.getSignature().getName() + result);
    }

}
