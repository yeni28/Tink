package com.ssafy.tink.dto;

import com.ssafy.tink.db.entity.Thumbnail;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PatternListDto {

	private int patternId;
	private String name;

	@Builder
	public PatternListDto(int patternId, String name) {
		this.name = name;
		this.patternId = patternId;
	}
}