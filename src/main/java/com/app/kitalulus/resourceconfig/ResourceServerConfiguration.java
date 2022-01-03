package com.app.kitalulus.resourceconfig;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import com.app.Kitalulus.constants.SecurityProperties;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@EnableConfigurationProperties(SecurityProperties.class)
@Slf4j
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

	@Autowired
	TokenStore tokenStore;
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		log.info("Configuring resource server..");
		resources.tokenStore(tokenStore);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

		log.info("Configuring http security..");
		http.authorizeRequests()
				.antMatchers("/h2-console/**", "/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
						"/configuration/security", "/swagger-ui.html", "/webjars/**")
				.permitAll().and().authorizeRequests().anyRequest().authenticated();
		http.csrf().disable().cors();
		http.headers().frameOptions().disable();
	}
}
