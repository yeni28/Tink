package com.ssafy.tink.dto;

import com.ssafy.tink.db.entity.Pattern;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ApiModel("PatternRecommendDto")
@Getter
@NoArgsConstructor
@ToString
public class YarnRecommendDto {

	Float gauge;
	Float gaugeDivisor;
	Float rowGauge;
	int yardage;
	int yardageMax;
	
	int patternId;

	@Builder
	public YarnRecommendDto(Pattern pattern) {
		this.gauge = pattern.getGauge();
		this.gaugeDivisor = pattern.getGaugeDivisor();
		this.rowGauge = pattern.getRowGauge();
		this.yardage = pattern.getYardage();
		this.yardageMax = pattern.getYardageMax();
		this.patternId = pattern.getPatternId();
	}

}
