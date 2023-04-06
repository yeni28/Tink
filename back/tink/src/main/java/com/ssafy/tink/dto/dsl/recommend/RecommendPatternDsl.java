package com.ssafy.tink.dto.dsl.recommend;

import java.util.HashSet;
import java.util.Set;

import com.querydsl.core.annotations.QueryProjection;
import com.ssafy.tink.db.entity.Member;
import com.ssafy.tink.dto.dsl.members.MemberInfoDsl;
import com.ssafy.tink.dto.dsl.members.PatternThumbInfoDsl;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) @Builder
@EqualsAndHashCode(of = "patternId")
public class RecommendPatternDsl {

	private int patternId;
	private String name;
	@Builder.Default
	private Set<PatternThumbInfoDsl> patternThumbInfoDslSet = new HashSet<>();
	@QueryProjection
	public RecommendPatternDsl(
		int patternId,
		String name,
		Set<PatternThumbInfoDsl> patternThumbInfoDslSet) {
		this.patternId = patternId;
		this.name = name;
		this.patternThumbInfoDslSet = patternThumbInfoDslSet;
	}
}
