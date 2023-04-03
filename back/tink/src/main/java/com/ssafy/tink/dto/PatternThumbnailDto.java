package com.ssafy.tink.dto;

import com.ssafy.tink.db.entity.PatternThumbnail;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ApiModel("PatternThumbnailDto")
@Getter
@ToString
@NoArgsConstructor
public class PatternThumbnailDto {

	private String mainImg;

	private String thumbImg;

	private int patternId;

	@Builder
	public PatternThumbnailDto(String mainImg, String thumbImg) {
		this.mainImg = mainImg;
		this.thumbImg = thumbImg;
	}

	public PatternThumbnailDto(PatternThumbnail thumbnail) {
		this.patternId = thumbnail.getPattern().getPatternId();
		this.mainImg = thumbnail.getMainImg();
		this.thumbImg = thumbnail.getThumbImg();
	}

}
