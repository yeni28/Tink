package com.ssafy.tink.service;

import java.util.Optional;

import com.ssafy.tink.dto.PatternDto;

public interface PatternService {

	int patternDelete(int patternId);

	int patternUpdate(PatternDto patternDto);

	Optional<PatternDto> getPatternList();

	Optional<PatternDto> getPatternDetail(int patternId);

	void patternLevelVote(int patternId, int memberId);



}
