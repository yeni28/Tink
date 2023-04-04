package com.ssafy.tink.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.tink.db.repository.PatternRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RecommendServiceImpl implements RecommendService{

	@Autowired
	private PatternRepository patternRepository;

	@Override
	public void getPatternByContentsFilter(Set<String> category, List<String> keyword,
		String difficulty) {

	}

}
