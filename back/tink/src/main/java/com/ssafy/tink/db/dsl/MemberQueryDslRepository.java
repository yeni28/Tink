package com.ssafy.tink.db.dsl;

import java.util.List;

import com.ssafy.tink.db.entity.Member;
import com.ssafy.tink.db.entity.Pattern;
import com.ssafy.tink.dto.dsl.members.BoardAndPatternDsl;
import com.ssafy.tink.dto.dsl.members.CommunityBoardInfoDsl;
import com.ssafy.tink.dto.dsl.members.MemberInfoDsl;
import com.ssafy.tink.dto.dsl.members.PatternInfoDsl;

public interface MemberQueryDslRepository {

	List<BoardAndPatternDsl> findBoardAndPatternListById(long memberId);
	List<MemberInfoDsl> findMember();
	List<CommunityBoardInfoDsl> findMypageCommunityBoardToById(long memberId);
	List<PatternInfoDsl> findPatternToRandom(String difficulty);
}
