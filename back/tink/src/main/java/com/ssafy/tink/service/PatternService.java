package com.ssafy.tink.service;

import java.util.List;
import java.util.Optional;

import com.ssafy.tink.db.entity.Thumbnail;
import com.ssafy.tink.dto.PatternDto;
import com.ssafy.tink.dto.PatternThumbnailDto;

public interface PatternService {

	void insertPattern(PatternDto patternDto, List<PatternThumbnailDto> thumbnail) throws Exception;

	void deletePattern(int patternId) throws Exception;

	void updatePattern(PatternDto patternDto) throws Exception;

	Optional<PatternDto> getPatternList();

	Optional<PatternDto> getPatternDetail(int patternId);

	void setLevelVote(int patternId, int memberId);


}
