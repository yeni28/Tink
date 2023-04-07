package com.ssafy.tink.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import lombok.RequiredArgsConstructor;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@RequiredArgsConstructor
public class SwaggerConfig extends WebMvcConfigurationSupport {

	private static final String SERVICE_TITLE = "Tink";
	private static final String SERVICE_DESCRIPTION = "뜨개질 추천 사이트";
	private static final String SERVICE_VERSION = "1.0.0";
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
			.useDefaultResponseMessages(false)
			.securityContexts(Arrays.asList(customSecurityContext()))
			.securitySchemes(Arrays.asList(apiKey()))	// security Authentication 설정
			.select()									// 문서화 API 탐색
			.apis(RequestHandlerSelectors.				// 문서화 API경로 지정
					basePackage("com.ssafy.tink"))		// 기본 패키지 지정
				.paths(PathSelectors.any())				// 문서화 API 조건 지정
			.build()
			.apiInfo(apiInfo());
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/swagger-ui.html")
			.addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").
			addResourceLocations("classpath:/META-INF/resources/webjars/");
		super.addResourceHandlers(registry);
	}

	// Authentication key 활성화 설정
	private ApiKey apiKey() {
		return new ApiKey("Authorization", "Authorization", "header");
	}

	private SecurityContext customSecurityContext() {
		return SecurityContext.builder()
			.securityReferences(defaultAuth())
			.build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("Authorization", authorizationScopes));
	}

	private ApiInfo apiInfo() {
		// api문서 타이틀, 설명, 버젼, 등등 설정
		return new ApiInfoBuilder()
			.title(SERVICE_TITLE)
			.description(SERVICE_DESCRIPTION)
			.version(SERVICE_VERSION)
			.contact(new Contact("welcome to tink", "http://example.com", "example@tink.com"))
			.build();
	}
}
