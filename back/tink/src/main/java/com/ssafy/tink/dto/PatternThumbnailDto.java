package com.ssafy.tink.dto;

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
	public PatternThumbnailDto(String mainImg, String thumbImg, int patternId) {
		this.mainImg = mainImg;
		this.thumbImg = thumbImg;
		this.patternId = patternId;
	}

}
