package com.edwin.springapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.edwin.springapi.security.RestAuthenticationEntryPoint;
import com.edwin.springapi.security.TokenAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecuriryConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public TokenAuthenticationFilter createTokenAuthenticationFilter () {
		return new TokenAuthenticationFilter();
	}
	
	@Bean
	public PasswordEncoder encriptarPassword () {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.cors()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
			.csrf()
				.disable()
			.formLogin()
				.disable()
			.httpBasic()
				.disable()
			.exceptionHandling()
				.authenticationEntryPoint(new RestAuthenticationEntryPoint())
				.and()
			.authorizeRequests()
				.antMatchers(
						"/login",
						"/signup"
						)
					.permitAll()
				.anyRequest()
					.authenticated()
					;
		
		http.addFilterBefore(createTokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		
	}
}
