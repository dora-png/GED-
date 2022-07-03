package com.microservice.ged.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private HandlerInterceptor traceinterceptor;

	@Override
	public void addCorsMappings(CorsRegistry corsRegistry) {
		corsRegistry.addMapping("/**").allowedOrigins("*").allowedMethods("*").maxAge(3600L)
				.allowedHeaders("*").exposedHeaders("*").allowCredentials(false);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(traceinterceptor);
	}

}

