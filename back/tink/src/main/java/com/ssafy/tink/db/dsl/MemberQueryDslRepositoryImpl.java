package com.ssafy.tink.db.dsl;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class MemberQueryDslRepositoryImpl implements MemberQueryDslRepository{

	private final JPAQueryFactory jpaQueryFactory;

	public MemberQueryDslRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
		this.jpaQueryFactory = jpaQueryFactory;
	}


}
