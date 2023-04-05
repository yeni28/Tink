package com.ssafy.tink.dto.dsl.members;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;

import com.querydsl.core.annotations.QueryProjection;
import com.ssafy.tink.db.entity.Member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@EqualsAndHashCode(of = "boardId")
public class BoardInfoDsl {

	private int boardId;
	private String boardCategory;
	private long memberId;
	private String title;
	private String content;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;

	@QueryProjection
	public BoardInfoDsl(
		int boardId,
		String boardCategory,
		long memberId,
		LocalDateTime createdDate,
		LocalDateTime updatedDate,
		String title,
		String content) {
		this.boardId = boardId;
		this.boardCategory = boardCategory;
		this.memberId = memberId;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.title = title;
		this.content = content;

	}

}
