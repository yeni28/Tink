package com.ssafy.tink.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

		http.csrf().disable();

		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/v2/api-docs/**","/webjars/**","/swagger-ui.html",
				"/configuration/**","/swagger-resources/**").permitAll()
			.anyRequest().authenticated();

		return http.build();
	}

}
