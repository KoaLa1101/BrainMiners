package ru.itlab.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Aspect
@Component
public class SignAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Before("execution(* ru.itlab.controllers.SignController.showSignUpForm(..))")
    public void logBefore(JoinPoint joinPoint){
        System.out.println("logs BEFORE by " + joinPoint.getSignature().getName());
        System.out.println("Registration start");
    }

    @AfterReturning(pointcut = "execution(* ru.itlab.controllers.SignController.addUser(..))",
    returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result){
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        String nameOfUser = principal.getName();
        System.out.println("logs AFTER_RETURNING by " + joinPoint.getSignature().getName());
        System.out.println("I know, that u " + nameOfUser);
        System.out.println(joinPoint.getSignature().getName() + result);
    }

    @Around("@annotation(ru.itlab.annotations.MyLog)")
        public Object logAfterProfile(ProceedingJoinPoint pjp) throws Throwable {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        String nameOfUser = principal.getName();

        String nameOfMethod = pjp.getSignature().toString().split(" ")[1];
        logger.info("logs by " + nameOfMethod + " Special for" + nameOfUser);
        return pjp.proceed();
    }
}
