package com.ssafy.tink.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.ssafy.tink.config.Util.SecurityUtil;
import com.ssafy.tink.dto.TokenDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RedisServiceImpl implements RedisService {
	private static final String TOKEN_HASH = "token";
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	public TokenDto setAccessWithRefresh(TokenDto token) {
		HashOperations<String, String, String> hash = redisTemplate.opsForHash();
		redisTemplate.opsForHash().put("RT:" + TOKEN_HASH, token.getMemberId(), token.getRefreshToken());
		return token;
	}

	public String getRefreshTokenByAuthentication(Long memberId) {
		HashOperations<String, String, String> hash = redisTemplate.opsForHash();
		String refresh = hash.get("RT:" + TOKEN_HASH, memberId);
		log.debug(refresh);
		return refresh;
	}
}
