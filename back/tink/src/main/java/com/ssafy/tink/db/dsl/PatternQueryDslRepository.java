package com.ssafy.tink.db.dsl;

import java.util.List;
import java.util.Set;

import com.ssafy.tink.dto.dsl.members.PatternInfoDsl;
import com.ssafy.tink.dto.dsl.recommend.RecommendPatternDsl;

public interface PatternQueryDslRepository {

	List<RecommendPatternDsl> getRecommendByContents(
		Set<String> category,
		List<String> keyword,
		String difficulty
	);
}
