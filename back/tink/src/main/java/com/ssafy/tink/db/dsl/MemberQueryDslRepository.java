package com.ssafy.tink.db.dsl;

import java.util.List;

import com.ssafy.tink.db.entity.Member;
import com.ssafy.tink.dto.dsl.members.BoardAndPatternDsl;
import com.ssafy.tink.dto.dsl.members.CommunityBoardInfoDsl;
import com.ssafy.tink.dto.dsl.members.MemberInfoDsl;

public interface MemberQueryDslRepository {

	List<BoardAndPatternDsl> findBoardAndPatternListById(long memberId);
	List<MemberInfoDsl> findMember();
	List<CommunityBoardInfoDsl> findMypageCommunityBoardToById(long memberId);
}
