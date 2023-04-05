package com.ssafy.tink;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;


@EnableFeignClients
@SpringBootApplication
@EnableJpaAuditing
@EnableRedisHttpSession
public class TinkApplication {

	public static void main(String[] args) {
		SpringApplication.run(TinkApplication.class, args);
	}

}
