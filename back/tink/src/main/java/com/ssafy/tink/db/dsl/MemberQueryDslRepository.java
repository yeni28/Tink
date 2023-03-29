package com.ssafy.tink.db.dsl;

import java.util.List;

import com.ssafy.tink.dto.dsl.members.BoardAndPatternDsl;
import com.ssafy.tink.dto.dsl.MemberInfoDsl;

public interface MemberQueryDslRepository {

	List<BoardAndPatternDsl> findBoardAndPatternListById(long memberId);
	List<MemberInfoDsl> findMember();

}
