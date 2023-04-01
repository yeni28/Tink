package com.ssafy.tink.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ssafy.tink.db.entity.Needle;
import com.ssafy.tink.db.entity.Pattern;
import com.ssafy.tink.db.entity.Thumbnail;
import com.ssafy.tink.db.repository.PatternRepository;
import com.ssafy.tink.dto.PatternDto;
import com.ssafy.tink.dto.PatternThumbnailDto;

@Service
public class PatternServiceImpl implements PatternService {

	@Autowired
	private PatternRepository patternRepository;

	@Transactional
	@Override
	public void deletePattern(int patternId) throws Exception{

		Optional<Pattern> pattern = patternRepository.findByPatternId(patternId);
		if(pattern.isPresent()){
			patternRepository.deleteById(patternId);
		}

	}

	@Override
	public void updatePattern(PatternDto patternDto) throws Exception{
	}

	@Override
	public Optional<PatternDto> getPatternList() {
		return Optional.empty();
	}

	@Override
	public Optional<PatternDto> getPatternDetail(int patternId) {
		Optional<Pattern> pattern = patternRepository.findByPatternId(patternId);

		if (!pattern.isPresent())
			return Optional.empty();

		return createPatternDto(pattern.get());
	}

	@Override
	public void setLevelVote(int patternId, int memberId) {

	}

	private Optional<PatternDto> createPatternDto(Pattern pattern) {

		List<Float> metrics = new ArrayList<>();
		for (Needle needle: pattern.getNeedles()) {
			metrics.add(needle.getMetric());
		}

		PatternDto patternDto = PatternDto.builder()
			.patternId(pattern.getPatternId())
			.guagePattern(pattern.getGaugePattern())
			.patternName(pattern.getName())
			.category(pattern.getCategory().getCategoryName())
			.gauge(pattern.getGauge())
			.gaugeDivisor(pattern.getGaugeDivisor())
			.notes_html(pattern.getNotesHtml())
			.rowGauge(pattern.getRowGauge())
			.yardage(pattern.getYardage())
			.yardageMax(pattern.getYardageMax())
			.parentCategory(pattern.getCategory().getCategoryName())
			.yarnWeightDescription(pattern.getYarnWeightDescription())
			.metric(metrics)
			.build();

		return Optional.ofNullable(patternDto);
	}

	@Override
	public void insertPattern(PatternDto patternDto, List<PatternThumbnailDto> thumbnail) throws Exception{

		//입력한 카데고리에 해당하는 Category를 넣음

		//바늘 테이블에 metric 삽입

		Pattern pattern = Pattern.builder()
			.gaugePattern(patternDto.getGuagePattern())
			.gauge(patternDto.getGauge())
			.gaugeDivisor(patternDto.getGaugeDivisor())
			.notesHtml(patternDto.getNotes_html())
			.rowGauge(patternDto.getRowGauge())
			.name(patternDto.getPatternName())
			.yardage(patternDto.getYardage())
			.yardageDescription(patternDto.getYarnWeightDescription())
			.build();


		patternRepository.save(pattern);
	}



}
