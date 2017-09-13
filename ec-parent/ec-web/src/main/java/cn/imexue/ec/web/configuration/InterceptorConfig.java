package cn.imexue.ec.web.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cn.imexue.ec.web.web.interceptor.LoginInterceptor;

@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter{

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor()).addPathPatterns("/**");
	}
	
	@Bean
	public HandlerInterceptor loginInterceptor(){
		return new LoginInterceptor();
	}
	
}
