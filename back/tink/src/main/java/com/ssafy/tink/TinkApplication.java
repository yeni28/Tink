package com.ssafy.tink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TinkApplication {
	public static void main(String[] args) {
		SpringApplication.run(TinkApplication.class,args);
	}
}
