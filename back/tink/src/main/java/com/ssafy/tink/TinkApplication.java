package com.ssafy.tink;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import com.ssafy.tink.service.CrawlingService;

@EnableFeignClients
@SpringBootApplication
@EnableJpaAuditing
@EnableRedisHttpSession
public class TinkApplication {

	// @Autowired
	// private CrawlingService crawlingService;

	public static void main(String[] args) {
		SpringApplication.run(TinkApplication.class, args);
	}

	// @PostConstruct
	// public void runCrawling(){
	// 	crawlingService.run();
	// }

}
