package com.ssafy.tink.config.oAuth;

import java.util.Map;

import org.springframework.security.core.Authentication;

import com.ssafy.tink.config.msg.AuthProvider;

public class OAuth2UserInfoFactory {
	/*
	* OAuth2 로그인 제공자에 맞는 UserInfo 반환하도록 처리
	* */
	public static OAuth2UserInfo create(AuthProvider authProvider, Map<String, Object> attributes) {
		switch(authProvider) {
			case GOOGLE:
				return new OAuth2GoogleUserInfo(attributes);
			default:
				return null;
		}
	}
}
