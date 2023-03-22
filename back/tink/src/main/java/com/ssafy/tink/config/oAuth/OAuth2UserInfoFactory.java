package com.ssafy.tink.config.oAuth;

import java.util.Map;

import org.springframework.security.core.Authentication;

import com.ssafy.tink.config.msg.AuthProvider;

public class OAuth2UserInfoFactory {

	public static OAuth2UserInfo create(AuthProvider authProvider, Map<String, Object> attributes) {
		switch(authProvider) {
			case GOOGLE:
				return new OAuth2GoogleUserInfo(attributes);
			default:
				return null;
		}
	}
}
