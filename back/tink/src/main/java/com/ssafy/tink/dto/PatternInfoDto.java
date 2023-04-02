package com.ssafy.tink.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ssafy.tink.db.entity.Pattern;
import com.ssafy.tink.db.entity.PatternThumbnail;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ApiModel("PatternInfoDto")
@ToString
@Getter
public class PatternInfoDto {

	private List<NeedleDto> needles = new ArrayList<>();
	private CategoryDto category;

	private List<PatternThumbnailDto> thumbnails = new ArrayList<>();

	private int id;

	private String name;
	private Float gauge;
	private Float gaugeDivisor;
	private String gaugePattern;
	private String sizesAvailable;
	private int yardage;
	private String yardageDescription;
	private int yardageMax;
	private String yarnWeightDescription;
	private String downloadUrl;
	private String notesHtml;
	private int difficultyCnt;
	private int difficultySum;
	private Float difficultyAvg;

	@Builder
	public PatternInfoDto(List<NeedleDto> needles, CategoryDto category, List<PatternThumbnailDto> thumbnails, int id,
		String name, Float gauge, Float gaugeDivisor, String gaugePattern, String sizesAvailable, int yardage,
		String yardageDescription, int yardageMax, String yarnWeightDescription, String downloadUrl, String notesHtml,
		int difficultyCnt, int difficultySum, Float difficultyAvg) {
		this.needles = needles;
		this.category = category;
		this.thumbnails = thumbnails;
		this.id = id;
		this.name = name;
		this.gauge = gauge;
		this.gaugeDivisor = gaugeDivisor;
		this.gaugePattern = gaugePattern;
		this.sizesAvailable = sizesAvailable;
		this.yardage = yardage;
		this.yardageDescription = yardageDescription;
		this.yardageMax = yardageMax;
		this.yarnWeightDescription = yarnWeightDescription;
		this.downloadUrl = downloadUrl;
		this.notesHtml = notesHtml;
		this.difficultyCnt = difficultyCnt;
		this.difficultySum = difficultySum;
		this.difficultyAvg = difficultyAvg;
	}

	@Builder
	public PatternInfoDto(List<PatternThumbnailDto> thumbnails, int id, String name) {
		this.thumbnails = thumbnails;
		this.id = id;
		this.name = name;
	}

	public PatternInfoDto(Pattern pattern) {
		this.id = pattern.getPatternId();
		this.name = pattern.getName();
		List<PatternThumbnail> thumbnail = pattern.getPatternThumbnails();
		this.thumbnails = thumbnail.stream().map(PatternThumbnailDto::new).collect(
			Collectors.toList());
	}

}
