package com.ssafy.tink.service;

import org.springframework.stereotype.Service;

import com.ssafy.tink.dto.TokenDto;

@Service
public interface RedisService {
	TokenDto setAccessWithRefresh(TokenDto token);

	String getRefreshTokenByAuthentication(Long memberId);
}
