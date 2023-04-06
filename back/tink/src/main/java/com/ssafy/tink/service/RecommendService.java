package com.ssafy.tink.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.ssafy.tink.dto.dsl.recommend.RecommendPatternDsl;

@Service
public interface RecommendService {

	List<RecommendPatternDsl> getPatternByContentsFilter(Set<String> category, List<String> keyword, String difficulty);

}
