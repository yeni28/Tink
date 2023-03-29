package com.ssafy.tink.dto.dsl;

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
public class BoardAndPatternDsl {

	private long memberId;
	@Builder.Default
	private List<BoardInfoDsl> board = new ArrayList<>();
	@Builder.Default
	private List<PatternInfoDsl> pattern = new ArrayList<>();

	@QueryProjection
	public BoardAndPatternDsl(long memberId, List<BoardInfoDsl> board, List<PatternInfoDsl> pattern) {
		this.memberId = memberId;
		this.board = board;
		this.pattern = pattern;
	}
}
