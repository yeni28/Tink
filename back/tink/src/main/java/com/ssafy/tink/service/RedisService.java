package com.ssafy.tink.service;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.ssafy.tink.config.Util.SecurityUtil;
import com.ssafy.tink.dto.TokenDto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@NoArgsConstructor
@AllArgsConstructor
public class RedisService {
	private static final String TOKEN_HASH = "token";
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	public TokenDto setAccessWithRefresh(TokenDto token) {
		HashOperations<String, String, String> hash = redisTemplate.opsForHash();
		redisTemplate.opsForHash().put(TOKEN_HASH, token.getRefresh(), token.getAccess());
		return token;
	}

	public void getRefreshTokenByAuthentication() {
		Optional<String> memberId = SecurityUtil.getCurrentAuthentication();
	}
}
