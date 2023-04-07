package com.ssafy.tink.config.oAuth;

import static com.ssafy.tink.config.oAuth.CustomAuthorizationRequestRepository.*;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.ssafy.tink.config.Util.CookieUtil;
import com.ssafy.tink.config.ect.BadRequestException;
import com.ssafy.tink.config.jwt.JwtTokenProvider;
import com.ssafy.tink.dto.TokenDto;
import com.ssafy.tink.service.RedisServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Value("${app.auth.oauth2.authorizedRedirectUri}")
	private String redirectUri;
	@Autowired
	private final JwtTokenProvider tokenProvider;
	@Autowired
	private final CustomAuthorizationRequestRepository authorizationRequestRepository;
	@Autowired
	private RedisServiceImpl redisServiceImpl;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws IOException, ServletException {
		// 토큰이 포함된 url을 생성 ex) http://localhost:3000/oauth2/redirect?accessToken={access-Token}
		String targetUrl = determineTargetUrl(request, response, authentication);

		if (response.isCommitted()) {
			log.debug("Response has already been committed");
			return;
		}
		// 쿠키에 저장된 정보를 지우는 작업실행
		clearAuthenticationAttributes(request, response);
		// redirectUri로 이동
		getRedirectStrategy().sendRedirect(request, response, targetUrl);
	}

	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) {
		// redirect_uri에 쿠키정보가 가져오기, 없으면 Optional.empty()을 가져옴
		Optional<String> redirectUri = CookieUtil.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
			.map(Cookie::getValue);
		// front에서 요청한 redirect_uri 와 server에 저장된 redirectUri가 일치하는지 확인
		if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
			throw new BadRequestException("redirect URIs are not matched");
		}
		// redirectUri가 없으면 -> defaultUri '/'
		String targetUrl = redirectUri.orElse(getDefaultTargetUrl());
		// 최초회원인지 확인하는 검사하는 부분
		OAuth2UserDetail userDetail = (OAuth2UserDetail)authentication.getPrincipal();
		// JWT 생성 ( accessToken, refreshToken )
		String accessToken = tokenProvider.createAccessToken(authentication);
		// refreshToken은 생성과 동시에 저장하도록 처리할 것임
		String refreshToken = tokenProvider.createRefreshToken(authentication, response);
		log.info("엑세스 토큰 값 : " + accessToken);
		log.info("리플래쉬 토큰 값 : " + refreshToken);
		// 리플래쉬 토큰을 레디스에 저장하는 부분
		redisServiceImpl.setAccessWithRefresh(TokenDto.builder()
											.memberId(userDetail.getId())
											.accessToken(accessToken)
											.refreshToken(refreshToken)
											.build()
		);
		// redirectUri에 query로 access토큰을 보줌
		return UriComponentsBuilder.fromUriString(targetUrl)
			.queryParam("isCheck",userDetail.isCheck())
			.queryParam("accessToken", accessToken)
			.build().toUriString();
	}

	protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
		super.clearAuthenticationAttributes(request);
		authorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
	}

	private boolean isAuthorizedRedirectUri(String uri) {
		URI clientRedirectUri = URI.create(uri);
		URI authorizedUri = URI.create(redirectUri);

		if (authorizedUri.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
			&& authorizedUri.getPort() == clientRedirectUri.getPort()) {
			return true;
		}
		return false;
	}
}
