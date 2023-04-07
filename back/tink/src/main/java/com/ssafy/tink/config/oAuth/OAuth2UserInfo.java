package com.ssafy.tink.config.oAuth;

import java.util.Map;

public abstract class OAuth2UserInfo {
	/*
	* 공통된 UserInfo 정보를 제공하는 추상클래스
	* */
	protected Map<String, Object> attributes;
	public OAuth2UserInfo(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
	public abstract String getId();
	public abstract String getName();
	public abstract String getEmail();
	public abstract String getImageUrl();
}
