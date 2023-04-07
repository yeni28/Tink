package com.ssafy.tink.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.tink.dto.BaseResponse;
import com.ssafy.tink.dto.QnaGroupInfoDto;
import com.ssafy.tink.dto.QnaGroupInputDto;
import com.ssafy.tink.dto.ReviewInfoDto;
import com.ssafy.tink.service.QnaGroupService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

	@Autowired
	private QnaGroupService qnaGroupService;

	@PostMapping("/{boardCategory}")
	@ApiOperation(value = "소모임글/질문글 작성")
	public BaseResponse<Object> write(
		@PathVariable final String boardCategory,
		@RequestBody QnaGroupInputDto QnaGroup) throws IOException {

		QnaGroup.setBoardCategory(boardCategory);

		if( QnaGroup.getTitle() == null || QnaGroup.getContent() == null) {
			BaseResponse.builder()
				.result("FAILED")
				.resultCode(HttpStatus.NOT_FOUND.value())
				.resultMsg("글 작성에 실패하였습니다.")
				.build();
		}

		qnaGroupService.create(QnaGroup);

		return BaseResponse.builder()
				.result("SUCCESS")
				.resultCode(HttpStatus.OK.value())
				.resultMsg("정상적으로 글이 작성되었습니다.")
				.build();
	}

	@PutMapping("/{boardCategory}")
	@ApiOperation(value = "소모임글/질문글 수정")
	public Object update(
		@RequestBody QnaGroupInputDto QnaGroup,
		@PathVariable final String boardCategory) {

		// thumbnail update..

		return BaseResponse.builder()
			.result(qnaGroupService.update(QnaGroup))
			.resultCode(HttpStatus.OK.value())
			.resultMsg("정상적으로 자랑글이 수정되었습니다.")
			.build();
	}

	@GetMapping("/{boardCategory}")
	@ApiOperation(value = "소모임글/질문글 상세 조회 API")
	public BaseResponse<Object> getReview(@RequestParam int boardId, @PathVariable final String boardCategory) {
		Optional<QnaGroupInfoDto> review = qnaGroupService.getReview(boardId);

		// 조횟수 증가
		qnaGroupService.updateView(boardId);

		if( !review.isPresent()) {
			BaseResponse.builder()
				.result("FAILED")
				.resultCode(HttpStatus.NOT_FOUND.value())
				.resultMsg("글을 가져오는데 실패하였습니다.")
				.build();
		}

		return BaseResponse.builder()
			.result(qnaGroupService.getReview(boardId))
			.resultCode(HttpStatus.OK.value())
			.resultMsg("정상적으로 조회되었습니다.")
			.build();
	}

	@GetMapping("/{boardCategory}/search")
	@ApiOperation(value = "소모임글/질문글 목록 필터 적용 API")
	public BaseResponse<Object> getReview(
		@RequestParam String filter,
		@PathVariable final String boardCategory) {

		return BaseResponse.builder()
			.result(qnaGroupService.getBoardList(filter,boardCategory))
			.resultCode(HttpStatus.OK.value())
			.resultMsg("정상적으로 조회되었습니다.")
			.build();
	}

	@DeleteMapping("/{boardCategory}")
	@ApiOperation(value = "소모임글/질문글 삭제")
	@ApiImplicitParam(name = "boardId", value = "자랑글 번호(PK)", required = true, dataType = "int", example = "1")
	public BaseResponse<Object> delete(@RequestParam(required = true) final int boardId, @PathVariable final String boardCategory) {

		return BaseResponse.builder()
			.result(qnaGroupService.delete(boardId))
			.resultCode(HttpStatus.OK.value())
			.resultMsg("정상적으로 삭제되었습니다.")
			.build();
	}


}

