package com.ssafy.tink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class TinkApplication {
	private static final String properties = "spring.config.location="
		+ "classpath:application-common.yml,"
		+ " classpath:application-local.yml,"
		+ " classpath:application-dev.yml";
	public static void main(String[] args) {
		new SpringApplicationBuilder(TinkApplication.class)
				.properties(properties)
				.run(args);
	}

}
