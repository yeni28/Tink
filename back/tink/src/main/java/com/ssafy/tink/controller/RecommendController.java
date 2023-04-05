package com.ssafy.tink.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.tink.config.DjangoClient;
import com.ssafy.tink.dto.BaseResponse;
import com.ssafy.tink.dto.PatternAndThumbnailDto;
import com.ssafy.tink.dto.PatternInfoDto;
import com.ssafy.tink.dto.PatternThumbnailDto;
import com.ssafy.tink.dto.UserPatternRecommendDto;
import com.ssafy.tink.dto.YarnRecommendDto;
import com.ssafy.tink.service.PatternService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/recommend")
public class RecommendController {

	public static final String CATEGORY = "category";
	public static final String KEYWORD = "keyword";
	public static final String DIFFICULT = "difficulty";
	@Autowired
	private DjangoClient djangoClient;
	@Autowired
	private PatternService patternService;

	@ApiOperation(value = "실 기반 추천")
	@PostMapping("/yarn")
	public BaseResponse<Object> sendPatternJsonForYarn(
		@RequestBody(required = false) YarnRecommendDto yarnRecommendDto) {
		List<YarnRecommendDto> getData = patternService.getPatternForRecommend();
		getData.add(yarnRecommendDto);//맨 마지막에는 사용자 데이터 넣기

		String jsonData = djangoClient.postPatternJsonForYarn(getData);

		//응답 JSON 파싱(유사도 정렬 추천 ID)
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			JsonNode node = objectMapper.readTree(jsonData);
			JsonNode recommendNode = node.get("recommendPatternId");

			if (recommendNode.isArray()) {
				List<PatternAndThumbnailDto> recommendResult = new ArrayList<>();
				for (JsonNode patternIdNode : recommendNode) {
					int patternId = patternIdNode.asInt();//숫자로 변환
					PatternAndThumbnailDto info = patternService.getPatternAndThumbnailList(patternId);
					recommendResult.add(info);
				}

				return BaseResponse.builder()
					.result(recommendResult)
					.resultCode(HttpStatus.OK.value())
					.resultMsg("추천 리스트 조회!!")
					.build();
			}
			return BaseResponse.builder()
				.result("FAILED")
				.resultCode(HttpStatus.NO_CONTENT.value())
				.resultMsg("추천 리스트 조회 실패!!")
				.build();

		} catch (JsonProcessingException e) {
			return BaseResponse.builder()
				.result("FAILED")
				.resultCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
				.resultMsg("JSON Parsing Error.")
				.build();
		}
	}

	@GetMapping("/patterns/contents")
	@ApiOperation(value = "컨텐츠 기반 도안 추천")
	public void getPatternJsonForYarn(@RequestBody Map<String, List<String>> filter) {

	}

	@GetMapping("/patterns/member")
	@ApiOperation(value = "사용자 기반 도안 추천")
	public BaseResponse<Object> getPatternForUser() {

		List<UserPatternRecommendDto> recommendPattern = patternService.getPatternForUserRecommend();

		return BaseResponse.builder()
			.result(recommendPattern)
			.resultCode(HttpStatus.OK.value())
			.resultMsg("사용자 기반 추천 리스트 반환 성공")
			.build();
	}


}
