package com.ssafy.tink.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.tink.dto.BaseResponse;
import com.ssafy.tink.dto.PatternDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;

@Api(value = "도안 API", tags = {"Pattern"})
@RestController
@RequestMapping("/patterns")
@Log4j2
public class PatternController {

	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE,
		MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "도안 등록", notes = "도안을 등록한다.")
	public ResponseEntity<BaseResponse<Object>> patternRegister() {
		return null;
	}

	@DeleteMapping()
	@ApiOperation(value = "도안 삭제", notes = "도안을 삭제한다.")
	public ResponseEntity<BaseResponse<Object>> patternDelete(@RequestParam int patternId) {
		return null;
	}

	@PutMapping()
	@ApiOperation(value = "도안 수정", notes = "도안을 수정한다.")
	public ResponseEntity<BaseResponse<Object>> patternUpdate(@RequestBody PatternDto patternDto) {
		return null;
	}

	@GetMapping("/search")
	@ApiOperation(value = "도안 조회", notes = "도안을 조회한다.")
	public ResponseEntity<BaseResponse<Object>> getPatternList(@RequestParam String writer,
		@RequestParam String keyword) {
		return null;
	}

	@GetMapping()
	@ApiOperation(value = "도안 상세 조회", notes = "도안을 상세 조회한다.")
	public ResponseEntity<BaseResponse<Object>> getPatternDetailList(@RequestParam int patternId) {
		return null;
	}

	@GetMapping("/like")
	@ApiOperation(value = "도안 좋아요", notes = "도안을 좋아요")
	public ResponseEntity<BaseResponse<Object>> setPatternLike(@RequestParam int patternId,
		@RequestHeader String accecssToken) {
		return null;
	}

	@GetMapping("/level")
	@ApiOperation(value = "도안 난이도 투표", notes = "도안 난이도를 투표한다.")
	public ResponseEntity<BaseResponse<Object>> setLevelVote(@RequestParam int difficultyCnt,
		@RequestParam int patternId) {
		return null;
	}

	@GetMapping("/best")
	@ApiOperation(value = "도안 주간 베스트", notes = "도안 주간 베스트를 조회한다.")
	public ResponseEntity<BaseResponse<Object>> getWeeklyBest() {
		return null;
	}

}
