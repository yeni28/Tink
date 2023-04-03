package com.ssafy.tink.dto;

import java.util.List;

import com.ssafy.tink.db.entity.Comment;
import com.ssafy.tink.db.entity.Material;
import com.ssafy.tink.db.entity.Member;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
// @AllArgsConstructor
public class QnaGroupInputDto {

	private int boardId;

	@ApiModelProperty(dataType = "String", example = "초보자 질문",required = true)
	private String title;
	@ApiModelProperty(dataType = "String", example = "겉뜨기 초보자 튜토리얼 도움되나요?",required = true)
	private String content;

	@ApiModelProperty(dataType = "String", example = "question")
	private String boardCategory;

	public void setBoardCategory(String boardCategory) {
		this.boardCategory = boardCategory;
	}

	@Builder
	public QnaGroupInputDto(int boardId, String title,String content, String boardCategory){
		this.boardId = boardId;
		this.title = title;
		this.content = content;
		this.boardCategory = boardCategory;
	}
}
