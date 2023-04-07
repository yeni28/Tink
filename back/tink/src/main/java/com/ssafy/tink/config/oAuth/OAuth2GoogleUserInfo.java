package com.ssafy.tink.config.oAuth;

import java.util.Map;

public class OAuth2GoogleUserInfo extends OAuth2UserInfo{
	/*
	* GOOGLE 회원 정보를 저장할 클래스
	* */
	public OAuth2GoogleUserInfo(Map<String, Object> attributes) {
		super(attributes);
	}

	@Override
	public String getId() {
		return (String) attributes.get("sub");
	}

	@Override
	public String getName() {
		return (String) attributes.get("name");
	}

	@Override
	public String getEmail() {
		return (String) attributes.get("email");
	}

	@Override
	public String getImageUrl() {
		return (String) attributes.get("picture");
	}
}
