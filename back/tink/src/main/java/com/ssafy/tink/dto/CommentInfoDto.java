package com.ssafy.tink.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
// @AllArgsConstructor
public class CommentInfoDto {

	private int commentId;
	private LocalDateTime createdDate;
	private String content;
	private String nickname;
	private String thumbnail;

	@Builder
	public CommentInfoDto(int commentId, LocalDateTime createdDate, String content, String nickname, String thumbnail) {
		this.commentId = commentId;
		this.nickname = nickname;
		this.thumbnail = thumbnail;
		this.content = content;
		this.createdDate = createdDate;
	}
}
