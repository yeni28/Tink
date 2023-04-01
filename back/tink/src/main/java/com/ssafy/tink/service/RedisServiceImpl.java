package com.ssafy.tink.service;

import java.util.Optional;

import com.ssafy.tink.config.Util.SecurityUtil;
import com.ssafy.tink.config.ect.BadRequestException;

public class RedisServiceImpl implements RedisService {

	@Override
	public void getRefreshTokenByRedisSession() {
		Optional<String> memberId = SecurityUtil.getCurrentAuthentication();
		if(!memberId.isPresent()) {
			throw new BadRequestException("로그인에 실패하였습니다.");
		}

	}
}
