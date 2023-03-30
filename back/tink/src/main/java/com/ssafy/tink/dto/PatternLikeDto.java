package com.ssafy.tink.dto;

import com.ssafy.tink.db.entity.Pattern;
import com.ssafy.tink.db.entity.PatternLikes;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@ApiModel("PatternLikeDTO")
@NoArgsConstructor
public class PatternLikeDto {

	Integer pattern;

	@Builder
	public PatternLikeDto(int patternId) {
		this.pattern = patternId;
	}
}
