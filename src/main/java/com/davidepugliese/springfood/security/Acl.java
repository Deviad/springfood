package com.davidepugliese.springfood.security;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})

public @interface Acl{
     String CURRENT_USER_ONLY = "CURRENT_USER_ONLY";
     String ADMINISTRATOR_ONLY = "ADMINISTRATOR_ONLY";
     String ADMINISTRATOR_OR_CURRENT_USER = "ADMINISTRATOR_OR_CURRENT_USER";
     String ALL_REGISTERED_USERS = "ALL_REGISTERED_USERS";

     String value();
}
