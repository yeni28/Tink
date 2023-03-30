package com.ssafy.tink.dto.dsl.members;

import com.querydsl.core.annotations.QueryProjection;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) @Builder
@EqualsAndHashCode(of = "patternThumbnailId")
public class PatternThumbInfoDsl {

	private int patternThumbnailId;
	private String mainImg;
	private String thumbImg;

	@QueryProjection
	public PatternThumbInfoDsl(
		int patternThumbnailId,
		String mainImg,
		String thumbImg) {
		this.patternThumbnailId = patternThumbnailId;
		this.mainImg = mainImg;
		this.thumbImg = thumbImg;
	}
}
