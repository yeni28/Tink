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
import com.ssafy.tink.config.oAuth.OAuth2AuthenticationFailureHandler;
import com.ssafy.tink.config.oAuth.OAuth2AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

	@Autowired
	private CustomAuthorizationRequestRepository cookieAuthorizationRequestRepository;
	@Autowired
	private CustomOAuth2UserService userService;
	@Autowired
	private OAuth2AuthenticationSuccessHandler authenticationSuccessHandler;
	@Autowired
	private OAuth2AuthenticationFailureHandler authenticationFailureHandler;

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
			.antMatchers("/oauth2/**").permitAll()
			.antMatchers("/file/**").hasRole("ROLE_USER")
			.anyRequest().authenticated();

		http.oauth2Login()
			// 인증된 정보를 가져오는 부분
			.authorizationEndpoint()
				.baseUri("/oauth2/authorization")	// OAuth2 기본URI
				.authorizationRequestRepository(cookieAuthorizationRequestRepository)
			// 특정 OAuth2 token을 받아오는 부분
			.and()
			.redirectionEndpoint()
				.baseUri("/*/oauth2/code/**")
			// OAuth2 server에서 받은 사용자 정보를 가지고 구현한 Service를 실행 ( 인증된 Authentication 객체 생성 )
			.and()
			.userInfoEndpoint()
				.userService(userService)
			// 성공했을 때 인증된 Authentication 객체를 가지고 토큰을 생성
			// 실패했을 때 핸들링 로직수행
			.and()
			.successHandler(authenticationSuccessHandler)
			.failureHandler(authenticationFailureHandler);

		return http.build();
	}

	public InMemoryUserDetailsManager userDetailsManager() {
		UserDetails admin = User.withDefaultPasswordEncoder()
			.username("admin")
			.password("ssafy")
			.roles("ADMIN")
			.build();

		UserDetails user = User.withDefaultPasswordEncoder()
			.username("tink")
			.password("ssafy")
			.roles("USER")
			.build();

		return new InMemoryUserDetailsManager(admin, user);
	}

}
