package com.ssafy.tink.db.dsl;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class MemberQueryDslRepositoryImpl implements MemberQueryDslRepository{

	private final JPAQueryFactory jpaQueryFactory;


}
