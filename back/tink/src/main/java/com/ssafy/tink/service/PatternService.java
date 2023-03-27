package com.ssafy.tink.service;

import com.ssafy.tink.dto.PatternDto;

public interface PatternService {

	void patternDelete(int patternId);

	void patternUpdate(PatternDto patternDto);

	void getPatternList();
}
