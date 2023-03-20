package com.ssafy.tink.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.jaas.memory.InMemoryConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

		http.csrf().disable()			// csrf 해제
			.formLogin().disable()		// form으로 로그인하는 방식 해제
			.httpBasic().disable()		//
			.sessionManagement()		// session 설정관리
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/v2/api-docs/**","/webjars/**","/swagger-ui.html",
				"/configuration/**","/swagger-resources/**").permitAll()
			.anyRequest().authenticated();

		return http.build();
	}

	public InMemoryUserDetailsManager userDetailsManager() {
		UserDetails user = User.withDefaultPasswordEncoder()
			.username("admin")
			.password("ssafy")
			.roles("USER")
			.build();
		return new InMemoryUserDetailsManager(user);
	}

}
