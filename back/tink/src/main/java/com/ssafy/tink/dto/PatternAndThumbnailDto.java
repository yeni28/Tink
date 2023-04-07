package com.ssafy.tink.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ssafy.tink.db.entity.Pattern;
import com.ssafy.tink.db.entity.PatternThumbnail;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@ApiModel("PatternAndThumbnailDto")
@Getter
@NoArgsConstructor
public class PatternAndThumbnailDto {

	private List<PatternThumbnailDto> thumbnails = new ArrayList<>();

	private int id;

	private String name;


	@Builder
	public PatternAndThumbnailDto(List<PatternThumbnailDto> thumbnails, int id, String name) {
		this.thumbnails = thumbnails;
		this.id = id;
		this.name = name;
	}

	public PatternAndThumbnailDto(Pattern pattern) {
		this.id = pattern.getPatternId();
		this.name = pattern.getName();
		List<PatternThumbnail> thumbnail = pattern.getPatternThumbnails();
		this.thumbnails = thumbnail.stream().map(PatternThumbnailDto::new).collect(
			Collectors.toList());
	}


}
