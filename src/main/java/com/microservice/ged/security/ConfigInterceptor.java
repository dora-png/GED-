package com.microservice.ged.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.microservice.ged.filter.TraceInterceptor;

@SuppressWarnings("deprecation")
@Component
public class ConfigInterceptor extends WebMvcConfigurerAdapter {

	@Autowired
	private TraceInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor);
    }
    
}
