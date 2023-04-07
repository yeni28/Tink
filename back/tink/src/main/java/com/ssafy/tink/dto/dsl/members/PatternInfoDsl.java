package com.ssafy.tink.dto.dsl.members;

import java.util.ArrayList;
import java.util.List;

import com.querydsl.core.annotations.QueryProjection;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) @Builder
@EqualsAndHashCode(of = "patternId")
public class PatternInfoDsl {

	private int patternId;
	private String name;
	private int categoryId;

	@Builder.Default
	private List<PatternThumbInfoDsl> patternThumbnails = new ArrayList<>();

	@QueryProjection
	public PatternInfoDsl(
		int patternId,
		String name,
		int categoryId,
		List<PatternThumbInfoDsl> patternThumbnails
		) {
		this.patternId = patternId;
		this.name = name;
		this.categoryId = categoryId;
		this.patternThumbnails = patternThumbnails;
	}
}
