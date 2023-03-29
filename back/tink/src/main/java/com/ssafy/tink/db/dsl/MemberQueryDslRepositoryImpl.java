package com.ssafy.tink.db.dsl;

import static com.querydsl.core.types.dsl.Expressions.*;
import static com.ssafy.tink.db.entity.QMember.member;
import static com.ssafy.tink.db.entity.QThumbnail.thumbnail;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.tink.dto.dsl.BoardAndPatternDsl;
import com.ssafy.tink.dto.dsl.MemberInfoDsl;
import com.ssafy.tink.dto.dsl.ThumbnailInfoDsl;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberQueryDslRepositoryImpl implements MemberQueryDslRepository{

	@Autowired
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<BoardAndPatternDsl> findBoardAndPatternList(long memberId) {

		jpaQueryFactory.from(member);


		return null;
	}

	@Override
	public List<MemberInfoDsl> findMember() {
		// List<MemberInfoDsl> result =  jpaQueryFactory.selectFrom(member)
		// 	.innerJoin(thumbnail).on(member.thumbnail.thumbnailId.eq(thumbnail.thumbnailId))
		// 	.transform(GroupBy.groupBy(member.memberId).as(new MemberInfoDsl(
		// 		member.memberId,
		// 		member.email,
		// 		list(new ThumbnailInfoDsl(thumbnail)
		// 	)));
		return null;
	}
}
