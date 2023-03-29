package com.ssafy.tink.db.dsl;

import static com.querydsl.core.group.GroupBy.*;
import static com.ssafy.tink.db.entity.QBoard.*;
import static com.ssafy.tink.db.entity.QMaterial.*;
import static com.ssafy.tink.db.entity.QMember.*;
import static com.ssafy.tink.db.entity.QPattern.*;
import static com.ssafy.tink.db.entity.QPatternThumbnail.*;
import static com.ssafy.tink.db.entity.QThumbnail.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.tink.db.entity.Member;
import com.ssafy.tink.db.entity.QMember;
import com.ssafy.tink.db.entity.QPatternThumbnail;
import com.ssafy.tink.dto.dsl.members.BoardAndPatternDsl;
import com.ssafy.tink.dto.dsl.MemberInfoDsl;
import com.ssafy.tink.dto.dsl.QMemberInfoDsl;
import com.ssafy.tink.dto.dsl.members.BoardInfoDsl;
import com.ssafy.tink.dto.dsl.members.CommunityBoardInfoDsl;
import com.ssafy.tink.dto.dsl.members.QBoardAndPatternDsl;
import com.ssafy.tink.dto.dsl.members.QBoardInfoDsl;
import com.ssafy.tink.dto.dsl.members.QCommunityBoardInfoDsl;
import com.ssafy.tink.dto.dsl.members.QPatternInfoDsl;
import com.ssafy.tink.dto.dsl.members.QPatternThumbInfoDsl;
import com.ssafy.tink.dto.dsl.members.QThumbnailInfoDsl;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberQueryDslRepositoryImpl implements MemberQueryDslRepository{

	private static final int MYPAGE_BOARD_LIMIT = 3;
	private static final String BOARD_CATEGORY_COMMUNTITY = "Communtity";
	private static final String BOARD_CATEGORY_QEUSTION = "Qna";
	private static final String BOARD_CATEGORY_GROUP = "Club";

	@Autowired
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<BoardAndPatternDsl> findBoardAndPatternListById(long memberId) {
		/*
		Map<Long, BoardAndPatternDsl> result =  jpaQueryFactory.selectFrom(member)
			.innerJoin(board).on(member.memberId.eq(board.member.memberId))
			.innerJoin(pattern).on(member.memberId.eq(pattern.member.memberId))
			.where(member.memberId.eq(memberId))
			.transform(groupBy(member.memberId).as(new QBoardAndPatternDsl(
				member.memberId,
				set(Projections.bean(CommunityBoardInfoDsl.class,
					ExpressionUtils.as(
						JPAExpressions.select(
							board.boardId,
							board.boardCategory,
							board.member.memberId,
							thumbnail,
							material)
							.from(board)
							.innerJoin(thumbnail).on(board.thumbnail.thumbnailId.eq(thumbnail.thumbnailId))
							.innerJoin(material).on(board.boardId.eq(material.board.boardId))
							.where(board.boardCategory.eq(BOARD_CATEGORY_COMMUNTITY))
						,"com1")).as("community")),
				set(Projections.bean(BoardInfoDsl.class,
					ExpressionUtils.as(
						JPAExpressions.select(
								board.boardId,
								board.boardCategory,
								board.member.memberId,
								board.createdDate,
								board.updatedDate)
							.from(board)
							.where(board.boardCategory.eq(BOARD_CATEGORY_QEUSTION))
						,"qna1")).as("question")),
				set(Projections.bean(BoardInfoDsl.class,
					ExpressionUtils.as(
						JPAExpressions.select(
								board.boardId,
								board.boardCategory,
								board.member.memberId,
								board.createdDate,
								board.updatedDate)
							.from(board)
							.where(board.boardCategory.eq(BOARD_CATEGORY_GROUP))
						,"gp1")).as("group")),
				set(new QPatternInfoDsl(pattern.patternId))
			)));
		*/
		Map<Long, BoardAndPatternDsl> result =  jpaQueryFactory.selectFrom(member)
			.join(board).on(member.memberId.eq(board.member.memberId))
			.join(pattern).on(member.memberId.eq(pattern.member.memberId))
			.where(
				member.memberId.eq(memberId)
				.and(board.boardCategory.in(BOARD_CATEGORY_QEUSTION,BOARD_CATEGORY_GROUP)))
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
}
