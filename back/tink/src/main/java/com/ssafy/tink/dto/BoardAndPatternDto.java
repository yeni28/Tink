package com.ssafy.tink.dto;

import java.util.List;

import com.ssafy.tink.db.entity.Board;
import com.ssafy.tink.db.entity.Pattern;
import com.ssafy.tink.dto.dsl.members.BoardAndPatternDsl;
import com.ssafy.tink.dto.dsl.members.CommunityBoardInfoDsl;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardAndPatternDto {

	private List<BoardAndPatternDsl> boardAndPatternDsl;
	private List<CommunityBoardInfoDsl> communityBoardInfoDsls;

	@Builder
	public BoardAndPatternDto(
		List<BoardAndPatternDsl> boardAndPatternDsl,
		List<CommunityBoardInfoDsl> communityBoardInfoDsls) {
		this.boardAndPatternDsl = boardAndPatternDsl;
		this.communityBoardInfoDsls = communityBoardInfoDsls;
	}

}
