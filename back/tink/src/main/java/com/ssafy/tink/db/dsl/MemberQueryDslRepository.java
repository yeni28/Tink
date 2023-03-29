package com.ssafy.tink.db.dsl;

import java.util.List;

import com.querydsl.core.Tuple;
import com.ssafy.tink.db.entity.Member;
import com.ssafy.tink.dto.dsl.BoardAndPatternDsl;
import com.ssafy.tink.dto.dsl.MemberInfoDsl;

public interface MemberQueryDslRepository {

	List<BoardAndPatternDsl> findBoardAndPatternList(long memberId);
	List<MemberInfoDsl> findMember();
}
