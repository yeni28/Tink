package com.ssafy.tink.dto;

import com.ssafy.tink.db.entity.Pattern;
import com.ssafy.tink.db.entity.PatternLikes;

import io.swagger.annotations.ApiModel;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("PatternLikeDTO")
public class PatternLikeDto {

	private int pattern;
	private int categoryId;

}
