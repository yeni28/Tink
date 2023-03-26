package com.ssafy.tink.db.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.tink.db.entity.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PatternRepositoryImpl implements PatternRepository{

    private final JPAQueryFactory queryFactory;


    @Override
    public Pattern searchDetail(int patternId) {
        return null;
    }
}
