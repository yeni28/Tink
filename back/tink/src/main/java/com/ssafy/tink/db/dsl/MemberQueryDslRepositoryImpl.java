package com.ssafy.tink.db.dsl;

import static com.querydsl.core.group.GroupBy.*;
import static com.ssafy.tink.db.entity.QBoard.*;
import static com.ssafy.tink.db.entity.QFollow.*;
import static com.ssafy.tink.db.entity.QMember.*;
import static com.ssafy.tink.db.entity.QPattern.*;
import static com.ssafy.tink.db.entity.QPatternThumbnail.*;
import static com.ssafy.tink.db.entity.QThumbnail.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.tink.dto.dsl.members.BoardAndPatternDsl;
import com.ssafy.tink.dto.dsl.members.BoardInfoDsl;
import com.ssafy.tink.dto.dsl.members.CommunityBoardInfoDsl;
import com.ssafy.tink.dto.dsl.members.FollowInfoDsl;
import com.ssafy.tink.dto.dsl.members.MemberInfoDsl;
import com.ssafy.tink.dto.dsl.members.PatternInfoDsl;
import com.ssafy.tink.dto.dsl.members.QBoardAndPatternDsl;
import com.ssafy.tink.dto.dsl.members.QBoardInfoDsl;
import com.ssafy.tink.dto.dsl.members.QCommunityBoardInfoDsl;
import com.ssafy.tink.dto.dsl.members.QFollowInfoDsl;
import com.ssafy.tink.dto.dsl.members.QMemberInfoDsl;
import com.ssafy.tink.dto.dsl.members.QPatternInfoDsl;
import com.ssafy.tink.dto.dsl.members.QPatternThumbInfoDsl;
import com.ssafy.tink.dto.dsl.members.QThumbnailInfoDsl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberQueryDslRepositoryImpl implements MemberQueryDslRepository {

	private static final int MYPAGE_BOARD_LIMIT = 3;
	private static final String BOARD_CATEGORY_COMMUNTITY = "review";
	private static final String BOARD_CATEGORY_QEUSTION = "question";
	private static final String BOARD_CATEGORY_GROUP = "group";

	@Autowired
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<BoardAndPatternDsl> findBoardAndPatternListById(long memberId) {
		BooleanBuilder builder = new BooleanBuilder();

		Map<Long, BoardAndPatternDsl> result = jpaQueryFactory.from(member)
			.join(board).on(member.memberId.eq(board.member.memberId))
			.join(pattern).on(member.memberId.eq(pattern.member.memberId)
				.and(board.boardCategory.in(
					BOARD_CATEGORY_QEUSTION, BOARD_CATEGORY_GROUP)))
			.leftJoin(patternThumbnail).on(pattern.patternId.eq(patternThumbnail.pattern.patternId))
			.where(member.memberId.eq(memberId))
			.transform(groupBy(member.memberId).as(new QBoardAndPatternDsl(
				member.memberId,
				set(new QBoardInfoDsl(
					board.boardId,
					board.boardCategory,
					board.member.memberId,
					board.createdDate,
					board.updatedDate,
					board.title,
					board.content)),
				set(new QPatternInfoDsl(
					pattern.patternId,
					pattern.name,
					pattern.category.categoryId,
					list(new QPatternThumbInfoDsl(
						patternThumbnail.patternThumbnailId,
						patternThumbnail.mainImg,
						patternThumbnail.thumbImg
					))
				))
			)));

		return result.keySet().stream()
			.map(result::get)
			.collect(Collectors.toList());

	}

	@Override
	public Optional<MemberInfoDsl> findMember(Long memberId) {
			Map<Long, MemberInfoDsl> result = jpaQueryFactory.selectFrom(member)
			.leftJoin(thumbnail).on(member.thumbnail.thumbnailId.eq(thumbnail.thumbnailId))
			.where(member.memberId.eq(memberId))
			.transform(groupBy(member.memberId).as(new QMemberInfoDsl(
				member.memberId,
				member.email,
				member.nickname,
				new QThumbnailInfoDsl(
					thumbnail.thumbnailId,
					thumbnail.mainImg,
					thumbnail.thumbImg
				))));

			MemberInfoDsl memberInfo = result.keySet().stream()
									.map(result::get)
									.findFirst().get();

			memberInfo.setFollows(jpaQueryFactory.select(follow.count())
									.from(follow)
									.where(follow.toId.castToNum(Long.class).eq(memberId))
									.fetchFirst());

			memberInfo.setFollower(jpaQueryFactory.select(follow.count())
									.from(follow)
									.where(follow.member.memberId.eq(memberId))
									.fetchFirst());

		return Optional.ofNullable(memberInfo);
	}

	public List<CommunityBoardInfoDsl> findMypageCommunityBoardToById(long memberId) {

		Map<Long, CommunityBoardInfoDsl> result = jpaQueryFactory.selectFrom(member)
			.join(board).on(member.memberId.eq(board.member.memberId))
			.leftJoin(thumbnail).on(board.thumbnail.thumbnailId.eq(thumbnail.thumbnailId))
			.where(member.memberId.eq(memberId)
				.and(board.boardCategory.eq(BOARD_CATEGORY_COMMUNTITY)))
			.transform(groupBy(member.memberId).as(new QCommunityBoardInfoDsl(
				board.boardId,
				board.boardCategory,
				board.member.memberId,
				board.title,
				board.content,
				board.createdDate,
				board.updatedDate,
				new QThumbnailInfoDsl(
					thumbnail.thumbnailId,
					thumbnail.mainImg,
					thumbnail.thumbImg)
			)));

		return result.keySet().stream()
			.map(result::get)
			.collect(Collectors.toList());
	}

	@Override
	public List<PatternInfoDsl> findPatternToRandom(String difficulty) {
		BooleanBuilder build = new BooleanBuilder();
		if( !difficulty.isEmpty() ) {
			build.and(pattern.difficultyAvg.castToNum(Double.class).lt(getdifficulty(difficulty)));
		}
		Map<Integer, PatternInfoDsl> patterns = jpaQueryFactory.selectFrom(pattern)
			.leftJoin(patternThumbnail)
				.on(pattern.patternId.eq(patternThumbnail.pattern.patternId))
			.where(build)
			.orderBy(pattern.createdDate.desc())
			.limit(1000)
			.transform(groupBy(pattern.patternId).as(new QPatternInfoDsl(
				pattern.patternId,
				pattern.name,
				pattern.category.categoryId,
				list(new QPatternThumbInfoDsl(
					patternThumbnail.patternThumbnailId,
					patternThumbnail.mainImg,
					patternThumbnail.thumbImg
				))
			)));
		List<PatternInfoDsl> list = patterns.keySet().stream()
			.map(patterns::get)
			.collect(Collectors.toList());
		// 도안을 섞어준다.!!
		Collections.shuffle(list);
		return list.stream().limit(30)
			.collect(Collectors.toList());
	}

	private int getdifficulty(String difficulty) {
		switch (difficulty) {
			case "low":
				return 3;
			case "middle":
				return 6;
			case "high":
				return 9;
			default:
				return 0;
		}
	}

	@Override
	public List<PatternInfoDsl> findPatternAllByMypage() {
		Map<Integer, PatternInfoDsl> result = jpaQueryFactory.from(pattern)
			.leftJoin(patternThumbnail).on(pattern.patternId.eq(patternThumbnail.pattern.patternId))
			.orderBy(pattern.createdDate.desc())
			.transform(groupBy(pattern.patternId).as(new QPatternInfoDsl(
				pattern.patternId,
				pattern.name,
				pattern.category.categoryId,
				list(new QPatternThumbInfoDsl(
					patternThumbnail.patternThumbnailId,
					patternThumbnail.mainImg,
					patternThumbnail.thumbImg
				)))
			));

		return result.keySet().stream()
			.map(result::get)
			.collect(Collectors.toList());
	}

	@Override
	public List<BoardInfoDsl> findBoardAllByMypage(String category) {
		Map<Integer, BoardInfoDsl> result = jpaQueryFactory.selectFrom(board)
			.where(board.boardCategory.eq(category))
			.orderBy(board.createdDate.desc())
			.transform(groupBy(board.boardId).as(new QBoardInfoDsl(
				board.boardId,
				board.boardCategory,
				board.member.memberId,
				board.createdDate,
				board.updatedDate,
				board.title,
				board.content
			)));
		return result.keySet().stream()
			.map(result::get)
			.collect(Collectors.toList());
	}

	@Override
	public List<CommunityBoardInfoDsl> findCommuntityAllByMypage() {
		Map<Integer, CommunityBoardInfoDsl> result = jpaQueryFactory.selectFrom(board)
			.leftJoin(thumbnail).on(thumbnail.thumbnailId.eq(board.thumbnail.thumbnailId))
			.where(board.boardCategory.eq(BOARD_CATEGORY_COMMUNTITY))
			.orderBy(board.createdDate.desc())
			.transform(groupBy(board.boardId).as(new QCommunityBoardInfoDsl(
				board.boardId,
				board.boardCategory,
				board.member.memberId,
				board.title,
				board.content,
				board.createdDate,
				board.updatedDate,
				new QThumbnailInfoDsl(
					thumbnail.thumbnailId,
					thumbnail.mainImg,
					thumbnail.thumbImg
				)
			)));
		return result.keySet().stream()
			.map(result::get)
			.collect(Collectors.toList());
	}

	@Override
	public Optional<FollowInfoDsl> existsFollow(Long memberId) {

		Map<Long, FollowInfoDsl> result = jpaQueryFactory.selectFrom(member)
			.leftJoin(follow).on(member.memberId.eq(follow.member.memberId))
			.where(member.memberId.eq(memberId))
			.transform(groupBy(member.memberId).as(new QFollowInfoDsl(
				member.memberId,
				set(new QMemberInfoDsl(
					follow.toId.castToNum(Long.class),
					member.email,
					member.nickname
				))
			)));
		return result.keySet().stream()
			.map(result::get)
			.findFirst();
	}
}
