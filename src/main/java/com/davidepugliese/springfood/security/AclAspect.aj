package com.davidepugliese.springfood.security;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AclAspect {
    @Pointcut(value = "@annotation(com.davidepugliese.springfood.security.Acl)" + "&& args(accLevel)")
    public void accessControl(Acl accLevel) {
    }

    @Around(value = "accessControl(accLevel)", argNames = "joinPoint,accLevel")
    public void value(ProceedingJoinPoint joinPoint, Acl accLevel) throws Throwable {
//        Object[] originalArguments = joinPoint.getArgs();
//
//        Object[] newArguments = new Object[1];
//        System.out.println(newArguments[0]);
//        newArguments[0] = ((String)originalArguments[0]).toUpperCase();

//        joinPoint.proceed(newArguments);

          System.out.println("Hello world!");
          joinPoint.proceed();

    }
}
