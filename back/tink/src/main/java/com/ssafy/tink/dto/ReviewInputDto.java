package com.ssafy.tink.dto;

import java.util.List;

import javax.persistence.Column;

import com.ssafy.tink.db.entity.Comment;
import com.ssafy.tink.db.entity.Material;
import com.ssafy.tink.db.entity.Member;
import com.ssafy.tink.db.entity.PatternThumbnail;
import com.ssafy.tink.db.entity.Thumbnail;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
// @AllArgsConstructor
public class ReviewInputDto {

	@ApiModelProperty(dataType = "String", example = "첫번째 목도리",required = true)
	private String title;
	@ApiModelProperty(dataType = "String", example = "직접 만즌 뜨개질 자랑할게요",required = true)
	private String content;

	@ApiModelProperty(dataType = "String", example = "review")
	private String boardCategory;

	// 재료 정보 (옵션)
	@ApiModelProperty(dataType = "String", example = "초록패턴실")
	private String yarnName;

	@ApiModelProperty(dataType = "Float", example = "5.5")
	private Float yarnWeight;

	@ApiModelProperty(dataType = "Float", example = "10.5")
	private Float yarnLength;

	@ApiModelProperty(dataType = "String", example = "대바늘")
	private String needle;

	@ApiModelProperty(dataType = "String", example = "5시간")
	private String time;

	private int patternId;


	public void setBoardCategory(String boardCategory) {
		this.boardCategory = boardCategory;
	}

	@Builder
	public ReviewInputDto(String title, String content, String boardCategory, String yarnName,
		Float yarnWeight, Float yarnLength, String needle, String time, int patternId) {
		this.title = title;
		this.content = content;
		this.boardCategory = boardCategory;
		this.yarnName = yarnName;
		this.yarnWeight = yarnWeight;
		this.yarnLength = yarnLength;
		this.needle = needle;
		this.time = time;
		this.patternId = patternId;
	}
}
