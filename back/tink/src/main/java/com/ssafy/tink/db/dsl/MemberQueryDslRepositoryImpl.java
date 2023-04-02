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

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.tink.dto.dsl.members.BoardAndPatternDsl;
import com.ssafy.tink.dto.dsl.members.CommunityBoardInfoDsl;
import com.ssafy.tink.dto.dsl.members.MemberInfoDsl;
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
public class MemberQueryDslRepositoryImpl implements MemberQueryDslRepository {

	private static final int MYPAGE_BOARD_LIMIT = 3;
	private static final String BOARD_CATEGORY_COMMUNTITY = "Commnuity";
	private static final String BOARD_CATEGORY_QEUSTION = "Qna";
	private static final String BOARD_CATEGORY_GROUP = "Club";

	@Autowired
	private final JPAQueryFactory jpaQueryFactory;
	@Autowired
	private final EntityManager entityManager;

	/*
		select *
		from member m join (select b.member_id, b.board_id, b.board_category, b.title, b.content, t.thumbnail_id, t.main_img, t.thumb_img, b.created_date, b.updated_date
							from board b left join jarang_img j on b.board_id = j.board_id
										left join thumbnail t on j.thumbnail_id = t.thumbnail_id
							where b.board_category = "Commnuity"
							UNION
							select b.member_id, b.board_id, b.board_category, b.title, b.content, t.thumbnail_id, t.main_img, t.thumb_img, b.created_date, b.updated_date
							from board b left join jarang_img j on j.board_id = b.board_id
										 left join thumbnail t on t.thumbnail_id = j.thumbnail_id
							where b.board_category IN ("Club","Qna")) as btm on btm.member_id = m.member_id
						join ( select p.pattern_id, pt.main_img, pt.thumb_img, p.member_id
							from pattern p left join pattern_thumbnail pt on p.pattern_id = pt.pattern_id) ppt on ppt.member_id = m.member_id
		where m.member_id = 2;
	* */
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
	public List<MemberInfoDsl> findMember() {
		Map<Long, MemberInfoDsl> result = jpaQueryFactory.selectFrom(member)
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

}
