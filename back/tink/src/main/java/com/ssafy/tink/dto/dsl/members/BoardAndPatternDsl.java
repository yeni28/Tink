package com.ssafy.tink.dto.dsl.members;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.querydsl.core.annotations.QueryProjection;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class BoardAndPatternDsl {

	private long memberId;
	private List<CommunityBoardInfoDsl> community;
	@Builder.Default
	private Set<BoardInfoDsl> board = new HashSet<>();
	@Builder.Default
	private Set<PatternInfoDsl> pattern = new HashSet<>();

	@QueryProjection
	public BoardAndPatternDsl(
		long memberId,
		Set<BoardInfoDsl> board,
		Set<PatternInfoDsl> pattern) {
		this.memberId = memberId;
		this.board = board;
		this.pattern = pattern;
	}
}
