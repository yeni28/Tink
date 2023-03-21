package com.ssafy.tink.config.oAuth;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import com.ssafy.tink.config.ect.OAuth2ProcessException;
import com.ssafy.tink.config.msg.AuthProvider;
import com.ssafy.tink.config.msg.MemberRole;
import com.ssafy.tink.test.Sample;
import com.ssafy.tink.test.SampleRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

	@Autowired
	private SampleRepository sampleRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User oAuth2User = super.loadUser(userRequest);
		return process(userRequest, oAuth2User);
	}

	public OAuth2User process(OAuth2UserRequest request, OAuth2User user) {
		AuthProvider authProvider = AuthProvider.valueOf(request.getClientRegistration().getRegistrationId().toUpperCase());
		OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.create(authProvider, user.getAttributes());

		if( oAuth2UserInfo == null ) {
			 throw new OAuth2ProcessException(" 이메일을 찾을 수 없습니다. ");
		}
		// 회원가입이 되어 있는지 DB확인
		Optional<Sample> sampleObj = sampleRepository.findByEmail(oAuth2UserInfo.getEmail());
		Sample sample = null;
		// 회원가입이 되어 있으면 해당 유저정보를 사용
		if( sampleObj.isPresent() ) {
			sample = sampleObj.get();
		}
		// 회원가입이 되어 있지않으면 해당 유저를 가입시키기
		else {
			sample = createUser(oAuth2UserInfo, authProvider);
		}
		return OAuth2UserDetail.create(sample, user.getAttributes());
	}

	public Sample createUser(OAuth2UserInfo userInfo, AuthProvider authProvider) {
		Sample sample = Sample.builder()
			.email(userInfo.getEmail())
			.role(MemberRole.USER)
			.authProvider(authProvider)
			.build();
		return sampleRepository.save(sample);

	}
}
