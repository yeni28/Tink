package com.ssafy.tink.dto;

import java.util.List;

import com.ssafy.tink.db.entity.Board;
import com.ssafy.tink.db.entity.Pattern;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardAndPatternDto {

	private List<Pattern> patternList;
	private List<Board> boardList;

	@Builder
	public BoardAndPatternDto(List<Pattern> patternList, List<Board> boardList) {
		this.patternList = patternList;
		this.boardList = boardList;
	}

}
