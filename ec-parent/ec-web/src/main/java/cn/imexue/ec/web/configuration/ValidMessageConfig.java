package cn.imexue.ec.web.configuration;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import cn.imexue.ec.web.web.param.DateConver;
import cn.imexue.ec.web.web.param.DateDeserializer;
import cn.imexue.ec.web.web.param.StringTrimConver;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;

@Configuration
public class ValidMessageConfig {

	@Value("${spring.jackson.date-format:yyyy-MM-dd hh:mm:ss}")
	private String dateFormatter;
	
	@Bean
	public LocalValidatorFactoryBean localValidatorFactoryBean(MessageSource messageSource){
		LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
		factoryBean.setValidationMessageSource(messageSource);
		return factoryBean;
	}
	
	@Bean
	public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder(){
		Jackson2ObjectMapperBuilder build = new Jackson2ObjectMapperBuilder();
		build.deserializerByType(String.class, new StringTrimConver());
		build.deserializerByType(Date.class, jsonDeserializer2());
		build.serializerByType(Date.class, jsonSerializer2());
		return build;
	}
	
	@Bean
	public JsonSerializer<Date> jsonSerializer2(){
		DateConver serial = new DateConver();
		serial.setFormat(dateFormatter);
		return serial;
	}
	
	@Bean
	public JsonDeserializer<Date> jsonDeserializer2(){
		DateDeserializer serial = new DateDeserializer();
		serial.setFormat(dateFormatter);
		return serial;
	}
}
