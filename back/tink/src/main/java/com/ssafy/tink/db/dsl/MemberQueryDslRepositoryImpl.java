package com.ssafy.tink.db.dsl;

import static com.querydsl.core.group.GroupBy.*;
import static com.ssafy.tink.db.entity.QBoard.*;
import static com.ssafy.tink.db.entity.QMember.*;
import static com.ssafy.tink.db.entity.QPattern.*;
import static com.ssafy.tink.db.entity.QPatternThumbnail.*;
import static com.ssafy.tink.db.entity.QThumbnail.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.tink.db.entity.QPatternThumbnail;
import com.ssafy.tink.db.entity.QThumbnail;
import com.ssafy.tink.dto.dsl.members.BoardAndPatternDsl;
import com.ssafy.tink.dto.dsl.members.BoardInfoDsl;
import com.ssafy.tink.dto.dsl.members.CommunityBoardInfoDsl;
import com.ssafy.tink.dto.dsl.members.MemberInfoDsl;
import com.ssafy.tink.dto.dsl.members.PatternInfoDsl;
import com.ssafy.tink.dto.dsl.members.QBoardAndPatternDsl;
import com.ssafy.tink.dto.dsl.members.QBoardInfoDsl;
import com.ssafy.tink.dto.dsl.members.QCommunityBoardInfoDsl;
import com.ssafy.tink.dto.dsl.members.QMemberInfoDsl;
import com.ssafy.tink.dto.dsl.members.QPatternInfoDsl;
import com.ssafy.tink.dto.dsl.members.QPatternThumbInfoDsl;
import com.ssafy.tink.dto.dsl.members.QThumbnailInfoDsl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberQueryDslRepositoryImpl implements MemberQueryDslRepository{

	private static final int MYPAGE_BOARD_LIMIT = 3;
	private static final String BOARD_CATEGORY_COMMUNTITY = "review";
	private static final String BOARD_CATEGORY_QEUSTION = "question";
	private static final String BOARD_CATEGORY_GROUP = "group";

	@Autowired
	private final JPAQueryFactory jpaQueryFactory;
	@Autowired
	private final EntityManager entityManager;

	@Override
	public List<BoardAndPatternDsl> findBoardAndPatternListById(long memberId) {
		BooleanBuilder builder =new BooleanBuilder();

		Map<Long, BoardAndPatternDsl> result =  jpaQueryFactory.from(member)
			.join(board).on(member.memberId.eq(board.member.memberId))
			.join(pattern).on(member.memberId.eq(pattern.member.memberId)
				.and(board.boardCategory.in(
					BOARD_CATEGORY_QEUSTION,BOARD_CATEGORY_GROUP)))
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
					list(new QPatternThumbInfoDsl(
						patternThumbnail.patternThumbnailId,
						patternThumbnail.main_img,
						patternThumbnail.thumb_img
					))
				))
			)));

		return result.keySet().stream()
			.map(result::get)
			.collect(Collectors.toList());
	}

	@Override
	public List<MemberInfoDsl> findMember() {
		Map<Long, MemberInfoDsl> result =  jpaQueryFactory.selectFrom(member)
			.innerJoin(thumbnail).on(member.thumbnail.thumbnailId.eq(thumbnail.thumbnailId))
			.transform(groupBy(member.memberId).as(new QMemberInfoDsl(
				member.memberId,
				member.email,
				list(new QThumbnailInfoDsl(
					thumbnail.thumbnailId,
					thumbnail.mainImg,
					thumbnail.thumbImg
				))
			)));
		return result.keySet().stream()
			.map(result::get)
			.collect(Collectors.toList());
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
			build.and(pattern.difficultyAvg.lt(getdifficulty(difficulty)));
		}
		Map<Integer, PatternInfoDsl> patterns = jpaQueryFactory.selectFrom(pattern)
			.leftJoin(patternThumbnail)
				.on(pattern.patternId.eq(patternThumbnail.patternThumbnailId))
			.where(build)
			.transform(groupBy(pattern.patternId).as(new QPatternInfoDsl(
				pattern.patternId,
				pattern.name,
				list(new QPatternThumbInfoDsl(
					patternThumbnail.patternThumbnailId,
					patternThumbnail.main_img,
					patternThumbnail.thumb_img
				))
			)));
		List<PatternInfoDsl> list = patterns.keySet().stream()
			.map(patterns::get)
			.collect(Collectors.toList());
		// 도안을 섞어준다.!!
		Collections.shuffle(list);
		return list;
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
				list(new QPatternThumbInfoDsl(
					patternThumbnail.patternThumbnailId,
					patternThumbnail.main_img,
					patternThumbnail.thumb_img
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
}
