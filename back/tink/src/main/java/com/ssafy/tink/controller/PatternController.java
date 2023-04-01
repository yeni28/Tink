package com.ssafy.tink.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.tink.db.entity.Thumbnail;
import com.ssafy.tink.dto.BaseResponse;
import com.ssafy.tink.dto.PageDto;
import com.ssafy.tink.dto.PatternDto;
import com.ssafy.tink.dto.PatternThumbnailDto;
import com.ssafy.tink.service.FileService;
import com.ssafy.tink.service.PatternService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j2;

@Api(value = "도안 API", tags = {"Pattern"})
@RestController
@RequestMapping("/patterns")
@Log4j2
public class PatternController {

	@Autowired
	private PatternService patternService;

	@Autowired
	private FileService fileService;

	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE,
		MediaType.MULTIPART_FORM_DATA_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "도안 등록", notes = "도안을 등록한다.")
	public BaseResponse<Object> patternRegister(@RequestHeader String accessToken, @RequestBody PatternDto patternDto,
		@RequestPart(required = false) List<MultipartFile> multipartFile) {

		if (multipartFile.isEmpty()) {
			return BaseResponse.builder()
				.result("FAILED")
				.resultCode(HttpStatus.NOT_FOUND.value())
				.resultMsg("이미지 데이터가 없습니다.")
				.build();
		}

		List<PatternThumbnailDto> fileList = new ArrayList<>();

		//다중 도안 이미지 처리
		for (MultipartFile file : multipartFile) {
			try {
				Thumbnail thumbnail = fileService.singleFileupload(file);
				PatternThumbnailDto dto = PatternThumbnailDto.builder()
					.thumbImg(thumbnail.getThumbImg())
					.mainImg(thumbnail.getMainImg())
					.build();

				fileList.add(dto);
			} catch (IOException e) {
				e.printStackTrace();
				return BaseResponse.builder()
					.result("FAILED")
					.resultCode(HttpStatus.NOT_FOUND.value())
					.resultMsg("데이터 삽입에 실패했습니다.")
					.build();
			}
		}

		try {
			patternService.insertPattern(patternDto, fileList);
		} catch (Exception e) {
			e.printStackTrace();

			return BaseResponse.builder()
				.result("FAILED")
				.resultCode(HttpStatus.NOT_FOUND.value())
				.resultMsg("데이터 삽입에 실패했습니다.")
				.build();
		}

		return BaseResponse.builder()
			.result("FAILED")
			.resultCode(HttpStatus.NOT_FOUND.value())
			.resultMsg("데이터 삽입에 실패했습니다.")
			.build();

	}

	@DeleteMapping("/{patternId}")
	@ApiOperation(value = "도안 삭제", notes = "도안을 삭제한다.")
	public BaseResponse<Object> patternDelete(@PathVariable @ApiParam(value = "도안 PK") int patternId) {

		try {
			patternService.deletePattern(patternId);

		} catch (Exception e) {
			return BaseResponse.builder()
				.result("FAILED")
				.resultCode(HttpStatus.NO_CONTENT.value())
				.resultMsg("삭제 실패했습니다")
				.build();
		}

		return BaseResponse.builder()
			.resultMsg("SUCCESS")
			.resultCode(HttpStatus.OK.value())
			.resultMsg("삭제 성공했습니다.")
			.build();
	}

	@PutMapping()
	@ApiOperation(value = "도안 수정", notes = "도안을 수정한다.")
	public BaseResponse<Object> patternUpdate(@RequestBody(required = false) PatternDto patternDto) {
		return null;
	}

	@GetMapping("/search")
	@ApiOperation(value = "도안 조회", notes = "도안을 조회한다.")
	public BaseResponse<Object> getPatternList(@RequestBody(required = false) PageDto pageDto) {

		return null;
	}

	@GetMapping("{patternId}")
	@ApiOperation(value = "도안 상세 조회", notes = "도안을 상세 조회한다.")
	public BaseResponse<Object> getPatternDetailList(@PathVariable @ApiParam(value = "도안 PK") int patternId) {

		Optional<PatternDto> patternDto = patternService.getPatternDetail(patternId);
		if (!patternDto.isPresent()) {
			return BaseResponse.builder()
				.result(patternDto.get())
				.resultCode(HttpStatus.NO_CONTENT.value())
				.resultMsg("조회한 결과가 없습니다.")
				.build();
		}

		return BaseResponse.builder()
			.result(patternDto.get())
			.resultCode(HttpStatus.OK.value())
			.resultMsg("정상적으로 조회되었습니다.")
			.build();
	}

	@GetMapping("/like")
	@ApiOperation(value = "도안 좋아요", notes = "도안을 좋아요")
	public BaseResponse<Object> setPatternLike(@RequestParam int patternId,
		@RequestHeader String accecssToken) {
		return null;
	}

	@GetMapping("/level")
	@ApiOperation(value = "도안 난이도 투표", notes = "도안 난이도를 투표한다.")
	public BaseResponse<Object> setLevelVote(@RequestParam int difficultyCnt,
		@RequestParam int patternId) {
		return null;
	}

	@GetMapping("/best")
	@ApiOperation(value = "도안 주간 베스트", notes = "도안 주간 베스트를 조회한다.")
	public BaseResponse<Object> getWeeklyBest() {
		return null;
	}

}
