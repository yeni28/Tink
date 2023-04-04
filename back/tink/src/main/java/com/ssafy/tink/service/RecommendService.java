package com.ssafy.tink.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public interface RecommendService {

	void getPatternByContentsFilter(Set<String> category, List<String> keyword, String difficulty);

}
