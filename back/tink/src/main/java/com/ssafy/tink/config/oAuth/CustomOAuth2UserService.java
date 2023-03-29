package com.ssafy.tink.config.oAuth;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.tink.config.Util.FileUtil;
import com.ssafy.tink.config.ect.OAuth2ProcessException;
import com.ssafy.tink.config.msg.AuthProvider;
import com.ssafy.tink.config.msg.MemberRole;
import com.ssafy.tink.db.entity.Member;
import com.ssafy.tink.db.entity.Thumbnail;
import com.ssafy.tink.db.repository.MemberRepository;
import com.ssafy.tink.db.repository.ThumbnailRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private ThumbnailRepository thumbnailRepository;
	@Value("${app.upload.folder}")
	private static String oAuth2PathImg;
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
		log.info("썸네일 이미지 저장");
		// OAuth2 이미지 저장하기
		/* SSL peer shut down incorrectly 에러 발생
		Optional<Thumbnail> thumbnail = uploadToThumbnail(oAuth2UserInfo, authProvider	);
		if( thumbnail.isPresent() ) {
			throw new OAuth2ProcessException("썸네일 저장에 실패하였습니다.");
		}
		*/
		// 회원가입이 되어 있는지 DB확인
		log.info("회원가입이 되어 있는지 DB확인");
		Optional<Member> memberObj = memberRepository.findByEmail(oAuth2UserInfo.getEmail());
		Member member = null;
		// 회원가입이 되어 있으면 해당 유저정보를 사용
		if( memberObj.isPresent() ) {
			member = memberObj.get();
		}
		// 회원가입이 되어 있지않으면 해당 유저를 가입시키기
		else {
			member = createUser(oAuth2UserInfo, authProvider);
		}
		return OAuth2UserDetail.create(member, user.getAttributes());
	}

	@Transactional
	public Member createUser( OAuth2UserInfo userInfo, AuthProvider authProvider) {

		Member member = Member.builder()
			.email(userInfo.getEmail())
			.nickname(userInfo.getName())
			.role(MemberRole.USER)
			.authProvider(authProvider)
			.thumbnail(Thumbnail.builder()
				.mainImg(userInfo.getImageUrl())
				.thumbImg(userInfo.getImageUrl())
				.build())
			.build();

		return memberRepository.save(member);
	}

	public Optional<Thumbnail> uploadToThumbnail(OAuth2UserInfo userInfo, AuthProvider authProvider) {
		try {
			String fileName = UUID.randomUUID().toString();
			URL url = new URL(userInfo.getImageUrl());
			BufferedInputStream image = new BufferedInputStream(url.openStream());
			BufferedImage origin = ImageIO.read(image);
			BufferedImage thumbnail = FileUtil.createThumbnail(userInfo.getImageUrl());
			if (thumbnail == null) {
				log.debug("OAuth2 썸네일 생성에 실패하였습니다.");
				return Optional.empty();
			}

			String path = FileUtil.createPath(oAuth2PathImg + File.separator + authProvider);
			FileUtil.saveImg(origin,path,fileName + FileUtil.FILEEXTENSION);
			FileUtil.saveImg(thumbnail,path,"thumb_" + fileName + FileUtil.FILEEXTENSION);

			Thumbnail thumb = Thumbnail.builder()
					.mainImg(path + File.separator + fileName + FileUtil.FILEEXTENSION)
					.thumbImg(path + File.separator + "thumb_" + fileName + FileUtil.FILEEXTENSION)
					.build();
			log.debug("저장된 파일 경로 : " + thumb.getMainImg());
			log.debug("저장된 파일 경로 : " + thumb.getThumbImg());
			return Optional.of(thumb);
		} catch(IOException e) {
			e.printStackTrace();
			log.debug("OAuth2 이미지 저장에 실패하였스빈다.");
			return Optional.empty();
		}
	}
}
