package com.ssafy.tink.config;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ssafy.tink.dto.PatternRecommendDto;

@FeignClient(name = "djangoClient", url = "http://localhost:8000/")
public interface DjangoClient {

	@PostMapping(value = "recommendByYarn/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	String postPatternJsonForYarn(@RequestBody List<PatternRecommendDto> pattern);

}
