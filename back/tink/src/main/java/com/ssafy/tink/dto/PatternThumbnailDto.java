package com.ssafy.tink.dto;

import com.ssafy.tink.db.entity.PatternThumbnail;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;

@ApiModel("PatternThumbnailDto")
@Getter
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
		this.mainImg = thumbnail.getMainImg();
		this.thumbImg = thumbnail.getThumbImg();
	}

}
