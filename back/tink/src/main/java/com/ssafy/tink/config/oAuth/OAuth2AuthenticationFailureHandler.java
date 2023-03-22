package com.ssafy.tink.config.oAuth;

import static com.ssafy.tink.config.oAuth.CustomAuthorizationRequestRepository.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.ssafy.tink.config.Util.CookieUtil;
@Component
public class OAuth2AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

	@Autowired
	private CustomAuthorizationRequestRepository authorizationRequestRepository;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException exception) throws IOException, ServletException {
			// 쿠키에 존재하는 redirectUri로 설정, 없으면 '/'로 이동
			String targetUrl = CookieUtil.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
				.map(Cookie::getValue)
				.orElse("/");
			// targetUrl에 query를 추가 error내용!!
			targetUrl = UriComponentsBuilder.fromUriString(targetUrl)
				.queryParam("error", exception.getLocalizedMessage())
				.build().toUriString();
			// 쿠키 삭제
			authorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
			// 타켓 url로 이동
			getRedirectStrategy().sendRedirect(request, response, targetUrl);
	}
}
