package com.ssafy.tink.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.tink.db.entity.Board;
import com.ssafy.tink.db.entity.Comment;
import com.ssafy.tink.dto.BaseResponse;
import com.ssafy.tink.dto.CommentInputDto;
import com.ssafy.tink.service.CommentService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping
	@ApiOperation(value = "댓글 작성")
	public BaseResponse<Object> writeComment(
		@RequestBody CommentInputDto comment) throws IOException {

		Optional<Board> board = commentService.findByBoardId(comment.getBoardId());

		if( !board.isPresent() || comment.getContent() == null) {
			return BaseResponse.builder()
				.result("FAILED")
				.resultCode(HttpStatus.NOT_FOUND.value())
				.resultMsg("댓글 작성에 실패하였습니다.")
				.build();
		}

		commentService.create(comment);

		return BaseResponse.builder()
				.resultCode(HttpStatus.OK.value())
				.resultMsg("정상적으로 자랑글이 작성되었습니다.")
				.build();
	}

	@DeleteMapping
	@ApiOperation(value = "댓글 삭제")
	@ApiImplicitParam(name = "commentId", value = " 댓글 번호(PK)", required = true, dataType = "int", example = "1")
	public BaseResponse<Object> deleteComment(@RequestParam(required = true) final int commentId) {

		Optional<Comment> comment = commentService.findByCommentId(commentId);

		if( !comment.isPresent()) {
			return BaseResponse.builder()
				.result("FAILED")
				.resultCode(HttpStatus.NOT_FOUND.value())
				.resultMsg("댓글 삭제에 실패하였습니다.")
				.build();
		}

		return BaseResponse.builder()
			.result(commentService.delete(commentId))
			.resultCode(HttpStatus.OK.value())
			.resultMsg("정상적으로 삭제되었습니다.")
			.build();
	}

}

