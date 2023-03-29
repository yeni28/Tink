package com.ssafy.tink.db.dsl;

import static com.querydsl.core.group.GroupBy.*;
import static com.ssafy.tink.db.entity.QBoard.*;
import static com.ssafy.tink.db.entity.QMember.*;
import static com.ssafy.tink.db.entity.QPattern.*;
import static com.ssafy.tink.db.entity.QPatternThumbnail.*;
import static com.ssafy.tink.db.entity.QThumbnail.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.tink.db.entity.Member;
import com.ssafy.tink.db.entity.Thumbnail;
import com.ssafy.tink.dto.dsl.members.BoardAndPatternDsl;
import com.ssafy.tink.dto.dsl.members.MemberInfoDsl;
import com.ssafy.tink.dto.dsl.members.QBoardAndPatternDsl;
import com.ssafy.tink.dto.dsl.members.QBoardInfoDsl;
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
	private static final String BOARD_CATEGORY_COMMUNTITY = "Communtity";
	private static final String BOARD_CATEGORY_QEUSTION = "Qna";
	private static final String BOARD_CATEGORY_GROUP = "Club";

	@Autowired
	private final JPAQueryFactory jpaQueryFactory;
	@Autowired
	private final EntityManager entityManager;

	@Override
	public List<BoardAndPatternDsl> findBoardAndPatternListById(long memberId) {
		BooleanBuilder builder =new BooleanBuilder();
		Map<Long, BoardAndPatternDsl> result =  jpaQueryFactory.from(member)
			.join(board).on(member.memberId.eq(board.member.memberId))
			.join(pattern).on(member.memberId.eq(pattern.member.memberId))
			.leftJoin(patternThumbnail).on(pattern.patternId.eq(patternThumbnail.pattern.patternId))
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
