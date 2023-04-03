package com.ssafy.tink.dto.dsl.members;

import java.sql.Timestamp;

import com.querydsl.core.annotations.QueryProjection;
import com.ssafy.tink.db.entity.Material;
import com.ssafy.tink.db.entity.Thumbnail;

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
public class CommunityBoardInfoDsl {

	private int boardId;
	private String boardCategory;
	private long memberId;
	private String createdDate;
	private String updatedDate;
	private String title;
	private String content;

	@Builder.Default
	private ThumbnailInfoDsl thumbnail = new ThumbnailInfoDsl();
	private MaterialInfoDsl material;

	@QueryProjection
	public CommunityBoardInfoDsl(
		int boardId,
		String boardCategory,
		long memberId,
		String title,
		String content,
		String createdDate,
		String updateDate,
		ThumbnailInfoDsl thumbnail) {
		this.boardId = boardId;
		this.boardCategory = boardCategory;
		this.memberId = memberId;
		this.createdDate = createdDate;
		this.updatedDate = updateDate;
		this.title = title;
		this.content = content;
		this.thumbnail = thumbnail;
	}

}
