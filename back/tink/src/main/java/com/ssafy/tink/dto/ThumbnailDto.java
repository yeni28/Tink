package com.ssafy.tink.dto;

import com.ssafy.tink.db.entity.PatternThumbnail;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ApiModel("ThumbnailDto")
@Getter
@ToString
@NoArgsConstructor
public class ThumbnailDto {

	private String mainImg;

	private String thumbImg;

	private int thumbnailId;

	@Builder
	public ThumbnailDto(String mainImg, String thumbImg) {
		this.mainImg = mainImg;
		this.thumbImg = thumbImg;
	}

	public ThumbnailDto(PatternThumbnail thumbnail) {
		this.thumbnailId = thumbnail.getPatternThumbnailId();
		this.mainImg = thumbnail.getMainImg();
		this.thumbImg = thumbnail.getThumbImg();
	}

}
