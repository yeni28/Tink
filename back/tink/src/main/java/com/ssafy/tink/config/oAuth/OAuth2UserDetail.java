package com.ssafy.tink.config.oAuth;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.ssafy.tink.db.entity.Member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OAuth2UserDetail implements UserDetails, OAuth2User {

	private Long		id;
	private String	email;
	private boolean	isCheck;

	private Collection<? extends GrantedAuthority> authorities;
	private Map<String, Object> attirbutes;

	public void setAttirbutes(Map<String,Object> attirbutes) {
		this.attirbutes = attirbutes;
	}

	public OAuth2UserDetail(
		Long id,
		String email,
		Collection<? extends GrantedAuthority> authorities,
		boolean isCheck) {
		this.id 			= 	id;
		this.email 			= 	email;
		this.authorities 	= 	authorities;
		this.isCheck		=	isCheck;
	}

	public static OAuth2UserDetail create(Member member, boolean isCheck) {
		// 소셜로 로그인한 사용자는 기본적으로 USER 권한으로 셋팅함
		Collection<? extends GrantedAuthority> authorities =
			Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
		// 유저의 아이디와 이메일을 저장한다.
		return new OAuth2UserDetail(
			member.getMemberId(),
			member.getEmail(),
			authorities,
			isCheck
		);
	}
	public static OAuth2UserDetail create(Member member, boolean isCheck, Map<String, Object> attributes) {
		OAuth2UserDetail userDetails = OAuth2UserDetail.create(member, isCheck);
		userDetails.setAttirbutes(attributes);
		return userDetails;
	}

	@Override
	public String getName() {
		return String.valueOf(this.id);
	}

	@Override
	public <A> A getAttribute(String name) {
		return OAuth2User.super.getAttribute(name);
	}

	@Override
	public Map<String, Object> getAttributes() {
		return this.attirbutes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.authorities;
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	public boolean isCheck() {
		return isCheck;
	}
}
