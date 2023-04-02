package com.ssafy.tink.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import com.ssafy.tink.config.Util.SecurityUtil;
import com.ssafy.tink.config.ect.BadRequestException;
import com.ssafy.tink.dto.TokenDto;

public class RedisServiceImpl implements RedisService {
	private static final String TOKEN_HASH = "token";
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	public TokenDto setAccessWithRefresh(TokenDto token) {
		HashOperations<String, String, String> hash = redisTemplate.opsForHash();
		redisTemplate.opsForHash().put(TOKEN_HASH, token.getRefreshToken(), token.getAccessToken());
		return token;
	}

	public void getRefreshTokenByAuthentication() {
		Optional<String> memberId = SecurityUtil.getCurrentAuthentication();
	}
}
