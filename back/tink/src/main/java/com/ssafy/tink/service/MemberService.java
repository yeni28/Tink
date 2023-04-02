package com.ssafy.tink.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.ssafy.tink.dto.BoardAndPatternDto;
import com.ssafy.tink.dto.MemberInfoDto;
import com.ssafy.tink.dto.PatternLikeDto;
import com.ssafy.tink.dto.TokenDto;
import com.ssafy.tink.dto.dsl.members.MemberInfoDsl;
import com.ssafy.tink.dto.dsl.members.PatternInfoDsl;

@Service
public interface MemberService {

	Optional<MemberInfoDto> getProfileByMemberId(long memberId);
	Optional<MemberInfoDto> getProfileByAuthentication();
	Optional<List<MemberInfoDsl>> getMemberInfoByQueryDsl();
	Optional<BoardAndPatternDto> getBoardAndPatternByAuthentication();
	Optional<BoardAndPatternDto> getBoardAndPatternByMemberId(long memberId);
	List<PatternInfoDsl> getFavoriteFromPattern(String difficulty);
	String likedPatternToMember(List<PatternLikeDto> patterns);
	TokenDto getRefreshToken(HttpSession session);
}
