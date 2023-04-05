package com.ssafy.tink.db.dsl;

import java.util.List;
import java.util.Optional;

import com.ssafy.tink.db.entity.Member;
import com.ssafy.tink.db.entity.Pattern;
import com.ssafy.tink.dto.dsl.members.BoardAndPatternDsl;
import com.ssafy.tink.dto.dsl.members.BoardInfoDsl;
import com.ssafy.tink.dto.dsl.members.CommunityBoardInfoDsl;
import com.ssafy.tink.dto.dsl.members.FollowInfoDsl;
import com.ssafy.tink.dto.dsl.members.MemberInfoDsl;
import com.ssafy.tink.dto.dsl.members.PatternInfoDsl;

public interface MemberQueryDslRepository {

	List<BoardAndPatternDsl> findBoardAndPatternListById(long memberId);
	Optional<MemberInfoDsl> findMember(Long memberId);
	List<CommunityBoardInfoDsl> findMypageCommunityBoardToById(long memberId);
	List<PatternInfoDsl> findPatternToRandom(String difficulty);
	List<PatternInfoDsl> findPatternAllByMypage();
	List<BoardInfoDsl> findBoardAllByMypage(String category);
	List<CommunityBoardInfoDsl> findCommuntityAllByMypage();
	Optional<FollowInfoDsl> existsFollow(Long memberId);
}
