package com.ssafy.tink.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
// @AllArgsConstructor
public class CommentInputDto {

	private int boardId;
	private String content;

	@Builder
	public CommentInputDto(int boardId, String content){
		this.boardId = boardId;
		this.content = content;
	}

}
