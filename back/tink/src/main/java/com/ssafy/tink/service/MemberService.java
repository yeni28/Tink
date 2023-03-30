package com.ssafy.tink.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.tink.config.Util.SecurityUtil;
import com.ssafy.tink.config.ect.BadRequestException;
import com.ssafy.tink.db.entity.Follow;
import com.ssafy.tink.db.entity.Member;
import com.ssafy.tink.db.repository.MemberRepository;
import com.ssafy.tink.dto.BoardAndPatternDto;
import com.ssafy.tink.dto.MemberInfoDto;
import com.ssafy.tink.dto.dsl.members.BoardAndPatternDsl;
import com.ssafy.tink.dto.dsl.members.CommunityBoardInfoDsl;
import com.ssafy.tink.dto.dsl.members.MemberInfoDsl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;

	@Transactional
	public Optional<MemberInfoDto> getProfileByMemberId(long memberId) {
		Optional<Member> member = memberRepository.findById(memberId);
		log.debug("회원 프로필 정보 조회결과 : " + member.toString());
		if( !member.isPresent() ) {
			return Optional.empty();
		}
		return createInfoByMember(member.get());
	}

	@Transactional
	public Optional<MemberInfoDto> getProfileByAuthentication() {
		return createInfoByMember(getMemberIdByAuthorization());
	}

	private Optional<MemberInfoDto> createInfoByMember(Member member) {

		long follows = 0;
		long follower = 0;

		if ( !member.getFollows().isEmpty() ) {
			Stream<Follow> stream = member.getFollows().stream();
			follows = stream.filter( follow -> follow.getToId() == member.getMemberId() ? true : false).count();
			follower = stream.filter( follow -> follow.getMember().getMemberId() == member.getMemberId() ? true : false).count();
		}

		MemberInfoDto memberInfo = MemberInfoDto.builder()
			.email(member.getEmail())
			.follows(follows)
			.follower(follower)
			.thumbnail(member.getThumbnail())
			.build();

		return Optional.ofNullable(memberInfo);
	}

	@Transactional
	public Optional<List<MemberInfoDsl>> getMemberInfoByQueryDsl() {
		return Optional.ofNullable(memberRepository.findMember());
	}

	@Transactional
	public Optional<List<BoardAndPatternDsl>> getBoardAndPatternByAuthentication() {
		Member member = getMemberIdByAuthorization();
		return Optional.ofNullable(memberRepository.findBoardAndPatternListById(member.getMemberId()));
	}

	@Transactional
	public Optional<BoardAndPatternDto> getBoardAndPatternByMemberId(long memberId) {
		List<BoardAndPatternDsl> boardAndPattern = memberRepository.findBoardAndPatternListById(memberId);
		List<CommunityBoardInfoDsl> communityBoardInfoDsls = memberRepository.findMypageCommunityBoardToById(memberId);
		return Optional.ofNullable(new BoardAndPatternDto(boardAndPattern, communityBoardInfoDsls));
	}

	private Member getMemberIdByAuthorization() {
		Optional<String> memberId = SecurityUtil.getCurrentAuthentication();
		Optional<Member> member = memberRepository.findById(Long.parseLong(memberId.get()));
		log.debug("회원 프로필 정보 조회결과 : " + member.toString());
		if( !member.isPresent() ) {
			throw new BadRequestException("찾는 회원의 정보가 없습니다.");
		}
		return member.get();
	}
}
