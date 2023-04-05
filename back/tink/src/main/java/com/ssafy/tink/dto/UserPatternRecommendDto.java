package com.ssafy.tink.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ssafy.tink.db.entity.Pattern;
import com.ssafy.tink.db.entity.PatternThumbnail;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel("UserPatternRecommendDto")
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserPatternRecommendDto {

	private int id;

	private String name;

	private List<PatternThumbnail> thumbnails;

	public UserPatternRecommendDto(Pattern pattern) {
		this.id = pattern.getPatternId();
		this.name = pattern.getName();
		this.thumbnails = pattern.getPatternThumbnails();
	}
}
