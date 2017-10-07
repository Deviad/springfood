package com.davidepugliese.springfood;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.util.TimeZone;

@SpringBootApplication
public class SpringfoodApplication {

	@PostConstruct
	void started() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
		objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
		jsonConverter.setObjectMapper(objectMapper);
		jsonConverter.setDefaultCharset(Charset.forName("UTF-8"));
		return jsonConverter;
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringfoodApplication.class, args);
	}
}
