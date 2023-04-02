package com.ssafy.tink.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ApiModel("PatternDTO")
@Getter
@NoArgsConstructor
public class PatternDto {

	int patternId;//pk

	@ApiModelProperty(value = "부모 카테고리")
	String parentCategory;//부모 카테고리

	@ApiModelProperty(value = "도안 카테고리")
	String category;//도안 카테고리

	@ApiModelProperty(value = "게이지 코수", example = "10.0")
	Float gauge;//게이지 코수
	@ApiModelProperty(value = "게이지 크기", example = "4.0")
	Float gaugeDivisor;//게이지 크기

	@ApiModelProperty(value = "게이지 패턴")
	String guagePattern;//게이지 패턴

	@ApiModelProperty(value = "게이지 단 수", example = "20.0")
	Float rowGauge;//게이지 단 수
	@ApiModelProperty(value = "도안 이름")
	String patternName;//도안 이름

	@ApiModelProperty(value = "도안 설명", example = "이 도안은 모자입니다.")
	String notesHtml;//도안 설명
	@ApiModelProperty(value = "실 길이", example = "100")
	int yardage;//실 길이

	@ApiModelProperty(value = "최대 길이", example = "200")
	int yardageMax;//최대 길이

	@ApiModelProperty(value = "실 굵기", example = "아란")
	String yarnWeightDescription;//실 굵기

	@ApiModelProperty(value = "바늘 사이즈", example = "3.5")
	Float metric;//바늘 사이즈(needle entity column)

	@Builder
	public PatternDto(int patternId, String parentCategory, String category, Float gauge, Float gaugeDivisor,
		String guagePattern, Float rowGauge, String patternName, String notesHtml, int yardage, int yardageMax,
		String yarnWeightDescription, Float metric) {
		this.patternId = patternId;
		this.parentCategory = parentCategory;
		this.category = category;
		this.gauge = gauge;
		this.gaugeDivisor = gaugeDivisor;
		this.guagePattern = guagePattern;
		this.rowGauge = rowGauge;
		this.patternName = patternName;
		this.notesHtml = notesHtml;
		this.yardage = yardage;
		this.yardageMax = yardageMax;
		this.yarnWeightDescription = yarnWeightDescription;
		this.metric = metric;
	}
}
