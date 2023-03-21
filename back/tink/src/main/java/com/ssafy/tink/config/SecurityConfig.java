package com.ssafy.tink.config;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.ssafy.tink.config.oAuth.CustomAuthorizationRequestRepository;
import com.ssafy.tink.config.oAuth.CustomOAuth2UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

	@Autowired
	private CustomAuthorizationRequestRepository authorizationRequestRepository;
	@Autowired
	private CustomOAuth2UserService userService;
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
			.antMatchers("/OAuth2/**").permitAll()
			.anyRequest().authenticated();

		http.oauth2Login()
			.authorizationEndpoint()
				.baseUri("/OAuth2/authorization")
				.authorizationRequestRepository(authorizationRequestRepository)

			.and()
			.redirectionEndpoint()
				.baseUri("/*/OAuth2/code/**")

			.and()
			.userInfoEndpoint()
				.userService(userService);

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
