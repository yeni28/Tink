package com.ssafy.tink.config.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	// 토큰이 저장된 헤더의 키값을 지정
	private static final String AUTHENTICATION_HEADER = "Authentication";
	// 토큰를 관리하는 Bean객체 주입 ( 제공자 )
	private final JwtTokenProvider jwtTokenProvider;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		// "Bearer "를 없애고 토큰만 가져옵니다.
		String accessToken = parseBearerToken(request);
		log.debug("토큰 검사 중입니다.!!");
		if( StringUtils.hasText(accessToken) && jwtTokenProvider.vailableToken(accessToken)) {
			// 토큰에서 Authentication 객체를 가져온다. ( Provider를 이용 )
			Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
			// 정보를 저장한 Authentication 객체를 SecurityContextHolder에 저장
			// 추후에 로그인한 회원의 정보는 SecurityContextHolder에에서 authentication 객체를 가져와야함
			SecurityContextHolder.getContext().setAuthentication(authentication);
			log.debug( authentication + " 인증정보 저장하였습니다.");
		} else {
			log.debug( "토큰 체크 중에 에러가 발생하였습니다.");
		}
		// 다음 필터를 처리하도록 이동
		filterChain.doFilter(request, response);
	}

	private String parseBearerToken(HttpServletRequest request) {
		// 헤더에서 토큰을 가져옴 ex) "Bearer ~~~~~~~~~~~~~~~~~~"
		String token = request.getHeader(AUTHENTICATION_HEADER);
		// 헤더를 정상적으로 가져온다면 "Bearer "를 없앤다.
		if(StringUtils.hasText(token) && token.startsWith("Bearer ")) {
			return token.substring(7);
		}
		return null;
	}
}
