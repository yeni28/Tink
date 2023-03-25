package com.ssafy.tink.config.Util;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ssafy.tink.config.jwt.JwtTokenProvider;
import com.ssafy.tink.config.oAuth.OAuth2UserDetail;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityUtil {

	public static Optional<String> getAuthenticationInfo() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if( authentication == null ) {
			log.debug("sorry, Authentication not found");
			return Optional.empty();
		}

		String memberId = null;
		if( authentication.getPrincipal() instanceof OAuth2UserDetail) {
			OAuth2UserDetail userDetail = (OAuth2UserDetail)authentication.getPrincipal();
			memberId = String.valueOf(userDetail.getId());
		} else if( authentication.getPrincipal() instanceof String ) {
			memberId = (String)authentication.getPrincipal();
		}
		return Optional.of(memberId);
	}
}
