package com.ssafy.tink.config;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ssafy.tink.dto.PatternRecommendDto;

@FeignClient(name = "djangoClient", url = "http://localhost:8000")
public interface DjangoClient {

	@PostMapping(value = "/recommendYarnByUser/", produces = "application/json", consumes = "application/json")
	String postPatternJsonForYarn(@RequestBody List<PatternRecommendDto> pattern);

}
