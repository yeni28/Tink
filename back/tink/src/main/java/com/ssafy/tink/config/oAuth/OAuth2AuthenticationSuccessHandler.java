package com.ssafy.tink.config.oAuth;

import static com.ssafy.tink.config.oAuth.CustomAuthorizationRequestRepository.*;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.ssafy.tink.config.Util.CookieUtil;
import com.ssafy.tink.config.ect.BadRequestException;
import com.ssafy.tink.config.jwt.JwtTokenProvider;

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

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) throws IOException, ServletException {
		String targetUrl = determineTargetUrl(request, response, authentication);

		if (response.isCommitted()) {
			log.debug("Response has already been committed");
			return;
		}
		clearAuthenticationAttributes(request, response);
		getRedirectStrategy().sendRedirect(request, response, targetUrl);
	}

	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response,
		Authentication authentication) {
		Optional<String> redirectUri = CookieUtil.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
			.map(Cookie::getValue);

		if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
			throw new BadRequestException("redirect URIs are not matched");
		}
		String targetUrl = redirectUri.orElse(getDefaultTargetUrl());

		// JWT 생성
		String accessToken = tokenProvider.createAccessToken(authentication);
		tokenProvider.createRefreshToken(authentication, response);

		return UriComponentsBuilder.fromUriString(targetUrl)
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
