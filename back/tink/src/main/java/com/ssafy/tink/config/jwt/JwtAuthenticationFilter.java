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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private static final String AUTHENTICATION_HEADER = "Authentication";
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
		FilterChain filterChain) throws ServletException, IOException {
		String accessToken = parseBearerToken(request);
		if( StringUtils.hasText(accessToken) && jwtTokenProvider.vailableToken(accessToken)) {
			Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			log.debug( authentication + " 인증정보 저장하였습니다.");
		} else {
			log.debug( "토큰 체크 중에 에러가 발생하였습니다.");
		}
	}

	private String parseBearerToken(HttpServletRequest request) {
		String token = request.getHeader(AUTHENTICATION_HEADER);
		if(StringUtils.hasText(token) && token.startsWith("Bearer ")) {
			return token.substring(7);
		}
		return null;
	}
}
