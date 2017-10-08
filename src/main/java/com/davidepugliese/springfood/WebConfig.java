package com.davidepugliese.springfood;

import com.davidepugliese.springfood.security.AclAspect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.json.UTF8DataInputJsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.Aspects;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.instrument.classloading.InstrumentationLoadTimeWeaver;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.web.servlet.config.annotation.*;

import javax.persistence.EntityManagerFactory;
import java.nio.charset.Charset;
import java.util.List;

@Configuration
@ComponentScan(basePackages = {"com.davidepugliese"})
//@EnableLoadTimeWeaving
@EnableAspectJAutoProxy
public class WebConfig extends WebMvcConfigurationSupport {


    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Bean
    public SessionFactory getSessionFactory() {
        if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        return entityManagerFactory.unwrap(SessionFactory.class);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/**")
                        .allowedOrigins("*")
                        .allowedMethods("POST", "GET", "PATCH", "PUT", "DELETE")
                        .allowedHeaders("header1", "header2", "header3")
                        .exposedHeaders("header1", "header2")
                        .allowCredentials(false).maxAge(0);
            }
        };
    }
    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns("/secure/*");

        return registrationBean;
    }

    @Bean
    public InstrumentationLoadTimeWeaver loadTimeWeaver()  throws Throwable {
        InstrumentationLoadTimeWeaver loadTimeWeaver = new InstrumentationLoadTimeWeaver();
        return loadTimeWeaver;
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String rootPath = System.getProperty("user.home");

        registry
                .addResourceHandler("/media/**")
                .addResourceLocations("file:" + rootPath + "/media/");
    }

//    @Bean
//    public AclAspect interceptor() {
//        AclAspect aspect = Aspects.aspectOf(AclAspect.class);
//        // ... inject dependencies here if not using @Autowired
//        return aspect;
//    }

}










