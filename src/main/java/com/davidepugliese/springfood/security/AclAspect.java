package com.davidepugliese.springfood.security;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import java.util.*;


@Component
@Aspect
public class AclAspect {
    @Pointcut(value = "@annotation(accLevel)")
    public void accessControl(Acl accLevel) {
    }

    @Around(value = "com.davidepugliese.springfood.security.AclAspect.accessControl(accLevel)")
    public Object value(ProceedingJoinPoint joinPoint, Acl accLevel) throws Throwable {
        Object[] originalArguments = joinPoint.getArgs();

        // Object[] newArguments = new Object[1];
        if (originalArguments.length > 0) {
            for (Object orginalArgument : originalArguments) {
                System.out.println(orginalArgument.toString()); 
                System.out.println(accLevel.value());
            }
        }
        
//        newArguments[0] = ((String)originalArguments[0]).toUpperCase();

//        joinPoint.proceed(newArguments);
        
        // assert Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws).getBody().getSubject().equals("Joe");
        
        System.out.println("Hello world!");
        return joinPoint.proceed();

    }
    
}
