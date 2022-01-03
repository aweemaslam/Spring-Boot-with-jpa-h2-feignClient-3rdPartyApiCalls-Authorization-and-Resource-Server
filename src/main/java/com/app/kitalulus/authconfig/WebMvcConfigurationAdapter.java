package com.app.kitalulus.authconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.app.kitalulus.httpinterceptor.HttpRateLimiterInterceptor;

@Configuration
public class WebMvcConfigurationAdapter extends WebMvcConfigurerAdapter {
	@Autowired
	HttpRateLimiterInterceptor httpInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		registry.addInterceptor(httpInterceptor);
	}
}