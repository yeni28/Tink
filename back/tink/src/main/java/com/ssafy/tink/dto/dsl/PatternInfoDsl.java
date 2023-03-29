package com.ssafy.tink.dto.dsl;

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

	@QueryProjection
	public PatternInfoDsl(int patternId) {
		this.patternId = patternId;
	}
}
