package com.ssafy.tink.db.dsl;

import static com.querydsl.core.group.GroupBy.*;
import static com.ssafy.tink.db.entity.QCategory.*;
import static com.ssafy.tink.db.entity.QKeyword.*;
import static com.ssafy.tink.db.entity.QPattern.*;
import static com.ssafy.tink.db.entity.QPatternThumbnail.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.tink.db.entity.Category;
import com.ssafy.tink.db.entity.Pattern;
import com.ssafy.tink.db.entity.QKeyword;
import com.ssafy.tink.db.entity.QPattern;
import com.ssafy.tink.db.entity.QPatternThumbnail;
import com.ssafy.tink.dto.dsl.members.PatternInfoDsl;
import com.ssafy.tink.dto.dsl.members.QMemberInfoDsl;
import com.ssafy.tink.dto.dsl.members.QPatternInfoDsl;
import com.ssafy.tink.dto.dsl.members.QPatternThumbInfoDsl;
import com.ssafy.tink.dto.dsl.members.ThumbnailInfoDsl;
import com.ssafy.tink.dto.dsl.recommend.QRecommendPatternDsl;
import com.ssafy.tink.dto.dsl.recommend.RecommendPatternDsl;

import io.jsonwebtoken.lang.Collections;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PatternQueryDslRepositoryImpl implements PatternQueryDslRepository{

	@Autowired
	private final JPAQueryFactory jpaQueryFactory;
	private static final String DIFFICULTY_LOW = "low";
	private static final String DIFFICULTY_MIDDLE = "middle";
	private static final String DIFFICULTY_HIGH = "high";

	/*
	* select *
	  from pattern p left join pattern_thumbnail pt on p.pattern_id = pt.pattern_id
			 		 left join pattern_keyword on p.pattern_id = pattern_keyword.pattern_id
	  where p.difficulty_avg between 3.0 and 6.0 and
		    p.category_id in (select category_id
							  from category
                              where category_name in ('Kitchen', 'Other Accessories')
                              ) or
	  p.pattern_id in (select pattern.pattern_id
							from pattern left join pattern_keyword on pattern.pattern_id = pattern_keyword.pattern_id
							left join keyword on keyword.keyword_id = pattern_keyword.keyword_id
						    where keyword.name in ('female','adult','collar','seamless'));
	* */
	@Override
	public List<RecommendPatternDsl> getRecommendByContents(
		Set<String> categorys,
		List<String> keywords,
		String difficulty) {

		BooleanBuilder builder = new BooleanBuilder();

		if( StringUtils.hasText(difficulty) ) {
			builder.and(isDifficultyEq(difficulty));
		}

		if( !Collections.isEmpty(categorys) ) {
			builder.and(isCategoryEq(categorys));
		}

		if( !Collections.isEmpty(keywords) ) {
			builder.andAnyOf(isKeywordEq(keywords));
		}

		Map<Integer, RecommendPatternDsl> patternList = jpaQueryFactory.selectFrom(pattern)
			.leftJoin(pattern.patternThumbnails, QPatternThumbnail.patternThumbnail)
			.where(builder)
			.distinct()
			.transform(groupBy(pattern.patternId).as(new QRecommendPatternDsl(
				pattern.patternId,
				pattern.name,
				set(new QPatternThumbInfoDsl(
					patternThumbnail.patternThumbnailId,
					patternThumbnail.mainImg,
					patternThumbnail.thumbImg
				))
			)));

		return patternList.keySet().stream().limit(16)
			.map(patternList::get)
			.collect(Collectors.toList());
	}

	private BooleanExpression isCategoryEq(Set<String> categorys) {
		return pattern.category.categoryId.in(JPAExpressions.select(category.categoryId)
			.from(category)
			.where(category.categoryName.in(categorys)));
	}

	private BooleanExpression isKeywordEq(List<String> keywords) {
		return pattern.keywords.any().name.in(keywords);
	}

	private BooleanExpression isDifficultyEq(String difficulty) {
		switch (difficulty) {
			case DIFFICULTY_LOW:
				return pattern.difficultyAvg.castToNum(Double.class).between(0,3.0);
			case DIFFICULTY_MIDDLE:
				return pattern.difficultyAvg.castToNum(Double.class).between(3.0,6.0);
			case DIFFICULTY_HIGH:
				return pattern.difficultyAvg.castToNum(Double.class).goe(6.0);
			default:
				return null;
		}
	}
}
