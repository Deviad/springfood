package com.davidepugliese.springfood.security;
import com.davidepugliese.springfood.adt.IEmail;
import com.davidepugliese.springfood.domain.UserDAO;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import java.util.*;
@Component
@Aspect
public class AclAspect {



    @Value("${jwt.secret}")
    private String secretKey;
    private UserDAO userService;

    @Autowired
    public AclAspect(UserDAO userService) {
        this.userService = userService;
    }



    @Pointcut(value = "@annotation(accLevel)")
    public void accessControl(Acl accLevel) {
    }

    @Around(value = "com.davidepugliese.springfood.security.AclAspect.accessControl(accLevel)")
    public Object value(ProceedingJoinPoint joinPoint, Acl accLevel) throws Throwable {




        Object[] originalArguments = joinPoint.getArgs();
        Object authorization = originalArguments[1];
        String[] parts = originalArguments[1].toString().split("Bearer ");
        // Object[] newArguments = new Object[1];
//        if (originalArguments.length > 0) {
//            for (Object orginalArgument : originalArguments) {
//                System.out.println(orginalArgument.toString());
//                System.out.println(accLevel.value());
//            }
//        }
        switch(accLevel.value()) {
            case Acl.CURRENT_USER_ONLY:
                String compactJws = parts[1];
                if( !Jwts.parser().setSigningKey(secretKey).parseClaimsJws(compactJws).getBody().getSubject().equals(originalArguments[0])) {
                    Map<String, Object> response = new HashMap<>();
                    response.put("status", "Access denied");
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
                }

        }


        
//        newArguments[0] = ((String)originalArguments[0]).toUpperCase();

//        joinPoint.proceed(newArguments);
        
        // assert Jwts.parser().setSigningKey(key).parseClaimsJws(compactJws).getBody().getSubject().equals("Joe");
        
        System.out.println("Hello world!");
        return joinPoint.proceed();

    }
    
}
