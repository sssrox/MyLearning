package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//	  auth.inMemoryAuthentication().withUser("shyam").password("123456").roles("ADMIN");
	
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//	  http.authorizeRequests()
//		.antMatchers("/upload").hasAnyRole("ADMIN")
//		.anyRequest().authenticated();
		
		http.authorizeRequests().antMatchers("/swagger-ui.html").permitAll();
		
		

	}
	
	@Override
	public void configure(WebSecurity  web) throws Exception {
		web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**");
	}
	

}
