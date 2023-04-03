package com.ssafy.tink.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.tink.config.DjangoClient;
import com.ssafy.tink.dto.PatternRecommendDto;
import com.ssafy.tink.service.PatternService;

@RestController
@RequestMapping("/recommend")
public class RecommendController {

	@Autowired
	private DjangoClient djangoClient;

	@Autowired
	private PatternService patternService;


	@GetMapping ("/")
	public String sendPatternJson(){
		List<PatternRecommendDto> getData = patternService.getPatternForRecommend();
		System.out.println(getData.toArray());

		return djangoClient.postPatternJsonForYarn(getData);
	}

}
