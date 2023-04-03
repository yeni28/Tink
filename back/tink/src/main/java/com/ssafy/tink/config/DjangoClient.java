package com.ssafy.tink.config;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ssafy.tink.dto.PatternRecommendDto;

@FeignClient(name = "djangoClient", url = "http://j8c201.p.ssafy.io:8000/")
public interface DjangoClient {

	@PostMapping(value = "/json", produces = "application/json", consumes = "application/json")
	String postJson(@RequestBody List<PatternRecommendDto> pattern, @RequestBody PatternRecommendDto userInput);

}
