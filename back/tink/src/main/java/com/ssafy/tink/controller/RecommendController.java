package com.ssafy.tink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.tink.config.DjangoClient;
import com.ssafy.tink.dto.PatternRecommendDto;
import com.ssafy.tink.service.PatternService;

@RestController("/recommend")
public class RecommendController {

	@Autowired
	private DjangoClient djangoClient;

	@Autowired
	private PatternService patternService;


	@PostMapping("/")
	public String sendPatternJson(@RequestBody PatternRecommendDto patternRecommendDto){
		List<PatternRecommendDto> getData = patternService.getPatternForRecommend();

		return djangoClient.postJson(getData, patternRecommendDto);
	}

}
