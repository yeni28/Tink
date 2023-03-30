package com.ssafy.tink.service;

import java.util.List;
import java.util.Optional;

import com.ssafy.tink.db.entity.Thumbnail;
import com.ssafy.tink.dto.PatternDto;
import com.ssafy.tink.dto.PatternThumbnailDto;

public interface PatternService {

	int insertPattern(PatternDto patternDto, List<PatternThumbnailDto> thumbnail);

	int deletePattern(int patternId);

	int updatePattern(PatternDto patternDto);

	Optional<PatternDto> getPatternList();

	Optional<PatternDto> getPatternDetail(int patternId);

	void setLevelVote(int patternId, int memberId);


}
