package com.ssafy.tink.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;

@ApiModel("PatternDTO")
@Getter
public class PatternDto {

	int patternId;//pk
	String parentCategory;//부모 카테고리
	String category;//도안 카테고리

	Float gauge;//게이지 코수
	Float gaugeDivisor;//게이지 크기
	String guagePattern;//게이지 패턴
	Float rowGauge;//게이지 단 수

	String patternName;//도안 이름
	String notes_html;//도안 설명

	int yardage;//실 길이
	int yardageMax;//최대 길이
	String yarnWeightDescription;//실 굵기

	List<Float> metric;//바늘 사이즈(needle entity column)


	@Builder
	public PatternDto(int patternId, String parentCategory, String category, Float gauge, Float gaugeDivisor,
		String guagePattern, Float rowGauge, String patternName, String notes_html, int yardage, int yardageMax,
		String yarnWeightDescription, List<Float> metric) {
		this.patternId = patternId;
		this.parentCategory = parentCategory;
		this.category = category;
		this.gauge = gauge;
		this.gaugeDivisor = gaugeDivisor;
		this.guagePattern = guagePattern;
		this.rowGauge = rowGauge;
		this.patternName = patternName;
		this.notes_html = notes_html;
		this.yardage = yardage;
		this.yardageMax = yardageMax;
		this.yarnWeightDescription = yarnWeightDescription;
		this.metric = metric;
	}
}
