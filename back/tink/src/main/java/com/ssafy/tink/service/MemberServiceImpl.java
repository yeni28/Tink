package com.ssafy.tink.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nimbusds.oauth2.sdk.util.StringUtils;
import com.ssafy.tink.config.Util.SecurityUtil;
import com.ssafy.tink.config.ect.BadRequestException;
import com.ssafy.tink.db.entity.Follow;
import com.ssafy.tink.db.entity.Member;
import com.ssafy.tink.db.entity.Pattern;
import com.ssafy.tink.db.entity.PatternLikeId;
import com.ssafy.tink.db.entity.PatternLikes;
import com.ssafy.tink.db.repository.MemberRepository;
import com.ssafy.tink.db.repository.PatternLikesRepository;
import com.ssafy.tink.dto.BaseResponse;
import com.ssafy.tink.dto.BoardAndPatternDto;
import com.ssafy.tink.dto.MemberInfoDto;
import com.ssafy.tink.dto.PatternLikeDto;
import com.ssafy.tink.dto.TokenDto;
import com.ssafy.tink.dto.dsl.members.BoardAndPatternDsl;
import com.ssafy.tink.dto.dsl.members.BoardInfoDsl;
import com.ssafy.tink.dto.dsl.members.CommunityBoardInfoDsl;
import com.ssafy.tink.dto.dsl.members.FollowInfoDsl;
import com.ssafy.tink.dto.dsl.members.MemberInfoDsl;
import com.ssafy.tink.dto.dsl.members.PatternInfoDsl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private PatternLikesRepository patternLikesRepository;
	private static final int PAGE_SIZE = 12;
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
		boolean isFollow = false;

		if ( !member.getFollows().isEmpty() ) {
			Stream<Follow> stream = member.getFollows().stream();
			follows = stream.filter( follow -> follow.getToId() == member.getMemberId() ? true : false).count();
			follower = stream.filter( follow -> follow.getMember().getMemberId() == member.getMemberId() ? true : false).count();
		}
		
		// 팔로우 여부 판별
		
		MemberInfoDto memberInfo = MemberInfoDto.builder()
			.email(member.getEmail())
			.nickname(member.getNickname())
			.follows(follows)
			.follower(follower)
			.thumbnail(member.getThumbnail())
			.isFollow(isFollow)
			.build();

		return Optional.ofNullable(memberInfo);
	}

	@Transactional
	public MemberInfoDsl getMemberInfoByQueryDsl(String memberId) throws NoSuchElementException {
		Optional<String> loginMember = SecurityUtil.getCurrentAuthentication();
		MemberInfoDsl memberInfo = null;
		// 유저정보를 토대로 정보 검색 ( "", null => false )
		if ( StringUtils.isNotBlank(memberId) ) {
			memberInfo = memberRepository.findMember(Long.parseLong(memberId)).get();
			memberInfo.setIsFollow(false);
			FollowInfoDsl follow = memberRepository.existsFollow(Long.parseLong(loginMember.get())).get();
			for(MemberInfoDsl follower : follow.getMember() ) {
				if ( follower.getMemberId() == memberInfo.getMemberId() ) {
					memberInfo.setIsFollow(true);
					break;
				}
			}
			return memberInfo;
		}
		memberInfo = memberRepository.findMember(Long.parseLong(loginMember.get())).get();
		memberInfo.setIsFollow(false);
		return memberInfo;
	}

	@Transactional
	public Optional<BoardAndPatternDto> getBoardAndPatternByAuthentication() {
		Member member = getMemberIdByAuthorization();
		List<BoardAndPatternDsl> boardAndPattern = memberRepository.findBoardAndPatternListById(member.getMemberId());
		List<CommunityBoardInfoDsl> communityBoardInfoDsls = memberRepository.findMypageCommunityBoardToById(member.getMemberId());
		return Optional.ofNullable(new BoardAndPatternDto(boardAndPattern, communityBoardInfoDsls));
		// return Optional.ofNullable(memberRepository.findBoardAndPatternListById(member.getMemberId()));
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

	@Transactional
	public List<PatternInfoDsl> getFavoriteFromPattern(String difficulty) {
		List<PatternInfoDsl> list = memberRepository.findPatternToRandom(difficulty);
		if(list.size() > PAGE_SIZE ) {
			List<PatternInfoDsl> randomList = list.stream().filter(pattern -> {
				if( pattern.getPatternId() % 2 == 0 ) {
					return true;
				}
				return false;
			}).collect(Collectors.toList());
			return randomList;
		}
		return list;
	}

	@Transactional
	public String likedPatternToMember(List<PatternLikeDto> patterns) {
		Member member = getMemberIdByAuthorization();
		Map<PatternLikeId, PatternLikes> likes = new HashMap<>();
		for(PatternLikeDto patternLike : patterns) {

			PatternLikeId id = new PatternLikeId(
				member.getMemberId(),
				patternLike.getPattern());

			PatternLikes Like = PatternLikes.builder()
				.member(member)
				.pattern(Pattern.builder()
						.patternId(patternLike.getPattern())
						.build())
				.build();

			likes.put(id, Like);
		}

		likes.keySet().stream()
			.map(likes::get)
			.forEach(patternLikesRepository::save);

		return "SUCCESS";
	}

	public TokenDto getRefreshToken(HttpSession session) {
		Optional<String> memberId = SecurityUtil.getCurrentAuthentication();
		String attr = session.getId();
		Object obj = session.getAttribute(session.getId());
		String refresh = (String) session.getAttribute(memberId.get());
		log.debug("session 가져온 값 : " + refresh);
		TokenDto token = TokenDto.builder()
			.refreshToken(refresh)
			.build();
		return token;
	}

	@Override
	@Transactional
	public List<PatternInfoDsl> getPatternAllByMypage() {
		return memberRepository.findPatternAllByMypage();
	}

	@Override
	@Transactional
	public List<BoardInfoDsl> getBoardAllByMypage(String category) {
		return memberRepository.findBoardAllByMypage(category);
	}

	@Override
	@Transactional
	public List<CommunityBoardInfoDsl> getCommnutityBoardAllByMypage() {
		return memberRepository.findCommuntityAllByMypage();
	}
}
