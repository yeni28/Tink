package com.ssafy.tink.config.Util;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ssafy.tink.config.oAuth.OAuth2UserDetail;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
public class SecurityUtil {

	public static Optional<String> getCurrentAuthentication() {
		// 사용자 정보를 가지고있는 authentication 객체를 가져와야합니다.
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// 만약 정보를 가져오지 못하였으면 빈 Optional 객체를 반환
		if( authentication == null ) {
			log.debug("sorry, Authentication not found");
			return Optional.empty();
		}
		// 사용자 ID를 저장할 변수지정
		String memberId = null;
		// authentication 객체의 타입이 OAuth2User인지 확인
		if( authentication.getPrincipal() instanceof OAuth2UserDetail) {
			// authentication 객체에 존재하는 OAuth2UserDetail 객체를 가져온다.
			OAuth2UserDetail userDetail = (OAuth2UserDetail)authentication.getPrincipal();
			// memberId를 저장
			memberId = String.valueOf(userDetail.getId());
		} else if ( authentication.getPrincipal() instanceof String) {
			memberId = (String) authentication.getPrincipal();
		}  else {
			log.debug("sorry, what is authentication Type?");
		}
		return Optional.ofNullable(memberId);
	}
}
