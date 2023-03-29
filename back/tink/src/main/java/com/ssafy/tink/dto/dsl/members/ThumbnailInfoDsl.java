package com.ssafy.tink.dto.dsl.members;

import com.querydsl.core.annotations.QueryProjection;
import com.querydsl.core.types.dsl.NumberPath;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) @Builder
@EqualsAndHashCode(of = "thumbnailId")
public class ThumbnailInfoDsl {

	private int thumbnailId;
	private String mainImg;
	private String thumbImg;

	@QueryProjection
	public ThumbnailInfoDsl(int thumbnailId, String mainImg, String thumbImg) {
		this.thumbnailId = thumbnailId;
		this.mainImg = mainImg;
		this.thumbImg = thumbImg;
	}
}
