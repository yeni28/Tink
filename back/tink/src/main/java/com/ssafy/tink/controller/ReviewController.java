package com.ssafy.tink.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.tink.db.entity.Board;
import com.ssafy.tink.dto.BaseResponse;
import com.ssafy.tink.dto.ReviewInfoDto;
import com.ssafy.tink.dto.ReviewInputDto;
import com.ssafy.tink.service.ReviewService;
import com.ssafy.tink.service.FileService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	@Autowired
	private FileService fileService;

	@PostMapping
	@ApiOperation(value = "자랑글 & 메인이미지 작성")
	public BaseResponse<Object> writeReview(
		@RequestPart ReviewInputDto community,
		@RequestPart("multipartFile") MultipartFile multipartFile) throws IOException {

		if( community.getTitle() == null || community.getContent() == null || community.getBoardCategory() == null) {
			BaseResponse.builder()
				.result("FAILED")
				.resultCode(HttpStatus.NOT_FOUND.value())
				.resultMsg("자랑글 작성에 실패하였습니다.")
				.build();
		}

		if(multipartFile != null){
			fileService.singleFileupload(multipartFile);
		}

		return BaseResponse.builder()
				.result(reviewService.create(community))
				.resultCode(HttpStatus.OK.value())
				.resultMsg("정상적으로 자랑글이 작성되었습니다.")
				.build();
	}

	@GetMapping("/patternList")
	@ApiOperation(value = "자랑글과 연관된 패턴 선택")
	public BaseResponse<Object> getPatternList(@RequestParam String filter) {

		return BaseResponse.builder()
			.result(reviewService.getPatternList(filter))
			.resultCode(HttpStatus.OK.value())
			.resultMsg("정상적으로 조회되었습니다.")
			.build();
	}

	@PutMapping
	@ApiOperation(value = "자랑글 수정")
	public Object update(
		@RequestBody ReviewInfoDto community,
		@RequestPart("multipartFile") MultipartFile multipartFile) {

		// thumbnail update..

		return BaseResponse.builder()
			.result(reviewService.update(community))
			.resultCode(HttpStatus.OK.value())
			.resultMsg("정상적으로 자랑글이 수정되었습니다.")
			.build();
	}

	@GetMapping
	@ApiOperation(value = "자랑글 상세 조회 API")
	public BaseResponse<Object> getReview(@RequestParam int boardId) {
		Optional<ReviewInfoDto> review = reviewService.getReview(boardId);

		// 조횟수 증가
		reviewService.updateView(boardId);

		if( !review.isPresent() || review.get() == null) {
			BaseResponse.builder()
				.result("FAILED")
				.resultCode(HttpStatus.NOT_FOUND.value())
				.resultMsg("자랑글을 가져오는데 실패하였습니다.")
				.build();
		}

		return BaseResponse.builder()
			.result(reviewService.getReview(boardId))
			.resultCode(HttpStatus.OK.value())
			.resultMsg("정상적으로 조회되었습니다.")
			.build();
	}

	@GetMapping("/search")
	@ApiOperation(value = "자랑글 목록 필터 적용 API")
	public BaseResponse<Object> getReview(@RequestParam String filter) {

		return BaseResponse.builder()
			.result(reviewService.getBoardList(filter, "review"))
			.resultCode(HttpStatus.OK.value())
			.resultMsg("정상적으로 조회되었습니다.")
			.build();
	}

	@DeleteMapping
	@ApiOperation(value = "자랑글 삭제")
	@ApiImplicitParam(name = "boardId", value = "자랑글 번호(PK)", required = true, dataType = "int", example = "1")
	public void delete(@RequestParam(required = true) final int boardId) {
		reviewService.delete(boardId);
	}

	@PostMapping("/likes")
	@ApiOperation(value = "자랑글의 좋아요 또는 좋아요 취소")
	public BaseResponse<Object> like(@RequestParam(required = true) final int boardId) {

		return BaseResponse.builder()
			.result(reviewService.like(boardId))
			.resultCode(HttpStatus.OK.value())
			.resultMsg("정상적으로 좋아요 되었습니다.")
			.build();
	}

}

