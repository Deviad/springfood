package com.davidepugliese.springfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;

@SpringBootApplication
public class SpringfoodApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringfoodApplication.class, args);
	}
}
