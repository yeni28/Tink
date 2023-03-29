package com.ssafy.tink.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ssafy.tink.config.jwt.JwtAccessDeniedHandler;
import com.ssafy.tink.config.jwt.JwtAuthenticationEntryPoint;
import com.ssafy.tink.config.jwt.JwtAuthenticationFilter;
import com.ssafy.tink.config.jwt.JwtTokenProvider;
import com.ssafy.tink.config.oAuth.CustomAuthorizationRequestRepository;
import com.ssafy.tink.config.oAuth.CustomOAuth2UserService;
import com.ssafy.tink.config.oAuth.OAuth2AuthenticationFailureHandler;
import com.ssafy.tink.config.oAuth.OAuth2AuthenticationSuccessHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	@Autowired
	private CustomAuthorizationRequestRepository cookieAuthorizationRequestRepository;
	@Autowired
	private CustomOAuth2UserService userService;
	@Autowired
	private OAuth2AuthenticationSuccessHandler authenticationSuccessHandler;
	@Autowired
	private OAuth2AuthenticationFailureHandler authenticationFailureHandler;
	@Autowired
	private JwtAccessDeniedHandler jwtAccessDeniedHandler;
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

		http.csrf().disable()			// csrf 해제
			.formLogin().disable()		// form으로 로그인하는 방식 해제
			.httpBasic().disable()		//
			.sessionManagement()		// session 설정관리
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		// 특정 API에 접근하는 권한을 확인하거나 permitAll을 설정하는 구간
		http.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/oauth2/**").permitAll()
			.antMatchers("/members/{id}","/members/info/{id}").permitAll()
			.antMatchers("/members/favorite/**").hasRole("USER")
			.antMatchers("/members/mypage/**").hasRole("USER")
			.antMatchers("/file/**").hasRole("USER")
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

		// 예외처리 처리하는 부분
		http.exceptionHandling()
			// 접근이 거부된 예외처리 로직 실행
			.accessDeniedHandler(jwtAccessDeniedHandler)
			// 인증 예외처리 로직 실행
			.authenticationEntryPoint(jwtAuthenticationEntryPoint);

		// 토큰을 확인하는 필터 추가
		http.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	public InMemoryUserDetailsManager userDetailsManager() {
		// 관리자 인메모리 회원
		UserDetails admin = User.withDefaultPasswordEncoder()
			.username("admin")
			.password("ssafy")
			.roles("ADMIN")
			.build();
		// 일반 회원 인메모리 유저
		UserDetails user = User.withDefaultPasswordEncoder()
			.username("tink")
			.password("ssafy")
			.roles("USER")
			.build();

		return new InMemoryUserDetailsManager(admin, user);
	}

	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		// 특정 uri를 무시 설정하는 구간
		return (web) -> web.ignoring()
			// swagger 관련 uri 무시하도록 처리
			.antMatchers(
				"/v2/api-docs",
				"/swagger-ui.html",
				"/swagger/**",
				"/webjars/**",
				"/swagger-resources/**",
				"/configuration/ui",
				"/configuration/security")
			// favicon.io 무시하도록 처리
			.antMatchers("favicon.io");
	}

}
