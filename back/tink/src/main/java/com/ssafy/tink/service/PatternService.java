package com.ssafy.tink.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ssafy.tink.config.Util.SecurityUtil;
import com.ssafy.tink.db.entity.Category;
import com.ssafy.tink.db.entity.DifficultyVote;
import com.ssafy.tink.db.entity.Member;
import com.ssafy.tink.db.entity.Needle;
import com.ssafy.tink.db.entity.Pattern;
import com.ssafy.tink.db.entity.PatternLike;
import com.ssafy.tink.db.entity.PatternThumbnail;
import com.ssafy.tink.db.repository.CategoryRepository;
import com.ssafy.tink.db.repository.DifficultyVoteRepository;
import com.ssafy.tink.db.repository.MemberRepository;
import com.ssafy.tink.db.repository.PatternLikeRepository;
import com.ssafy.tink.db.repository.PatternRepository;
import com.ssafy.tink.db.repository.PatternThumbnailRepository;
import com.ssafy.tink.dto.CategoryDto;
import com.ssafy.tink.dto.NeedleDto;
import com.ssafy.tink.dto.PageDto;
import com.ssafy.tink.dto.PatternDto;
import com.ssafy.tink.dto.PatternInfoDto;
import com.ssafy.tink.dto.PatternThumbnailDto;

@Service
public class PatternService {

	@Autowired
	private PatternRepository patternRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private DifficultyVoteRepository difficultyVoteRepository;

	@Autowired
	private PatternThumbnailRepository patternThumbnailRepository;
	@Autowired
	private PatternLikeRepository patternLikeRepository;

	@Transactional
	public void deletePattern(int patternId) throws Exception {

		Optional<String> memberId = SecurityUtil.getCurrentAuthentication();
		Optional<Member> member = memberRepository.findById(Long.parseLong(memberId.get()));

		Optional<Pattern> getPattern = patternRepository.findByPattern(patternId,
			member.get().getMemberId());

		if (!getPattern.isPresent()) {
			throw new Exception();
		}

		//해당 도안을 등록한 사용자가 아닌 경우
		if (!getPattern.get().getMember().getMemberId().equals(member.get().getMemberId())) {
			throw new Exception();
		}

		patternRepository.deleteById(patternId);

	}

	public void updatePattern(PatternDto patternDto, List<PatternThumbnailDto> fileList) throws Exception {
		Optional<String> memberId = SecurityUtil.getCurrentAuthentication();
		Optional<Member> member = memberRepository.findById(Long.parseLong(memberId.get()));

		Optional<Pattern> getPattern = patternRepository.findByPattern(patternDto.getPatternId(),
			member.get().getMemberId());

		if (!getPattern.isPresent()) {
			throw new Exception();
		}

		Pattern updatePattern = getPattern.get();

		updatePattern.setName(patternDto.getPatternName());
		updatePattern.setGaugePattern(patternDto.getGuagePattern());
		updatePattern.setGauge(patternDto.getGauge());
		updatePattern.setGaugeDivisor(patternDto.getGaugeDivisor());
		updatePattern.setNotesHtml(patternDto.getNotesHtml());
		updatePattern.setRowGauge(patternDto.getRowGauge());
		updatePattern.setYardage(patternDto.getYardage());
		updatePattern.setYardageMax(patternDto.getYardageMax());

		//파일 수정 처리
		for (PatternThumbnailDto dto : fileList) {
			PatternThumbnail request = PatternThumbnail.builder()
				.mainImg(dto.getMainImg())
				.pattern(updatePattern)
				.thumbImg(dto.getThumbImg())
				.build();
			updatePattern.addPatternThumbnail(request);
		}

		patternRepository.save(updatePattern);
	}

	@Transactional
	public List<PatternInfoDto> getPatternList(PageDto pageDto) {

		int pageSize = 12;
		Page<Pattern> patterns = new PageImpl<>(new ArrayList<>());
		String sort = "";

		if (pageDto.getKeyword() != null && pageDto.getSortType() != null && !pageDto.getSortType().equals("")
			&& !pageDto.getKeyword().equals("")) {//검색조건과 정렬방식이 존재하는 경우
			if (pageDto.getSortType().equals("created_date")) {
				sort = "createdDate";
			} else if (pageDto.getSortType().equals("name")) {
				sort = "name";
			} else {//인기 순 정렬
				Pageable pageable = PageRequest.of(pageDto.getNowPageNum(), pageSize);
				List<Pattern> results = patternRepository.findKeywordAndOrderByLikes(pageDto.getKeyword(), pageable);

				return results.stream().map(PatternInfoDto::new).collect(
					Collectors.toList());
			}
			Pageable pageable = PageRequest.of(pageDto.getNowPageNum(), pageSize,
				Sort.by(sort).descending());
			patterns = patternRepository.searchByKeyword(pageDto.getKeyword(), pageable);

		} else if (pageDto.getKeyword() != null && !pageDto.getKeyword().equals("")) {//검색 조건만 있는 경우
			Pageable pageable = PageRequest.of(pageDto.getNowPageNum(), pageSize);
			patterns = patternRepository.searchByKeyword(pageDto.getKeyword(), pageable);

		} else if (pageDto.getSortType() != null && !pageDto.getSortType().equals("")) {//정렬 조건이 있는 경우
			if (pageDto.getSortType().equals("created_date")) {
				sort = "createdDate";
			} else if (pageDto.getSortType().equals("name")) {
				sort = "name";
			} else {
				//인기 순 정렬
				Pageable pageable = PageRequest.of(pageDto.getNowPageNum(), pageSize);
				List<Pattern> results = patternRepository.findOrderByLikes(pageable);

				return results.stream().map(PatternInfoDto::new).collect(
					Collectors.toList());
			}
			Pageable pageable = PageRequest.of(pageDto.getNowPageNum(), pageSize,
				Sort.by(sort).descending());

			patterns = patternRepository.searchAll(pageable);

		} else {
			//검색조건과 정렬방식이 없는 경우
			Pageable pageable = PageRequest.of(pageDto.getNowPageNum(), pageSize);
			patterns = patternRepository.searchAll(pageable);
		}

		return patterns.stream().map(PatternInfoDto::new).collect(
			Collectors.toList());
	}

	@Transactional
	public PatternInfoDto getPatternDetail(int patternId) {
		Optional<Pattern> pattern = patternRepository.findByPatternId(patternId);//카데고리, 도안, 바늘 정보 전부 가져옴
		if (!pattern.isPresent())
			return null;

		//needle response setting
		List<NeedleDto> needleDtoList = new ArrayList<>();

		for (Needle needle : pattern.get().getNeedles()) {
			NeedleDto dto = NeedleDto.builder()
				.us(needle.getUs())
				.usSteel(needle.getUsSteel())
				.prettyMetric(needle.getPrettyMetric())
				.metric(needle.getMetric())
				.name(needle.getName())
				.build();
			needleDtoList.add(dto);
		}

		//category response setting
		Category category = pattern.get().getCategory();
		Category parentCategory = category.getParent();//상위 카테고리 가져옴
		CategoryDto parent = null;

		if (parentCategory != null) {//가장 상위 카테고리는 null이므로 확인해야함
			Optional<Category> optionalParentCategory = categoryRepository.findByCategoryId(
				parentCategory.getCategoryId());

			if (optionalParentCategory.isPresent()) {//상위 카테고리 정보 가져옴
				parent = CategoryDto.builder()
					.id(optionalParentCategory.get().getCategoryId())
					.categoryName(optionalParentCategory.get().getCategoryName())
					.build();
			}
		}

		CategoryDto categoryDto = CategoryDto.builder()
			.categoryName(category.getCategoryName())
			.depth(category.getDepth())
			.parentCategory(parent)
			.build();

		//thumbnail info response setting
		List<PatternThumbnail> thumbnails = pattern.get().getPatternThumbnails();
		List<PatternThumbnailDto> thumbnailList = new ArrayList<>();

		for (PatternThumbnail response : thumbnails) {
			PatternThumbnailDto dto = PatternThumbnailDto.builder()
				.thumbImg(response.getThumbImg())
				.mainImg(response.getMainImg())
				.build();

			thumbnailList.add(dto);
		}

		Pattern patternInfo = pattern.get();
		PatternInfoDto info = PatternInfoDto.builder()
			.id(patternInfo.getPatternId())
			.difficultySum(patternInfo.getDifficultySum())
			.difficultyCnt(patternInfo.getDifficultyCnt())
			.notesHtml(patternInfo.getNotesHtml())
			.yardageMax(patternInfo.getYardageMax())
			.gaugePattern(patternInfo.getGaugePattern())
			.downloadUrl(patternInfo.getDownloadUrl())
			.difficultyAvg(patternInfo.getDifficultyAvg())
			.sizesAvailable(patternInfo.getSizesAvailable())
			.yarnWeightDescription(patternInfo.getYarnWeightDescription())
			.yardage(patternInfo.getYardage())
			.yardageDescription(patternInfo.getYardageDescription())
			.gaugeDivisor(patternInfo.getGaugeDivisor())
			.gauge(patternInfo.getGauge())
			.gaugePattern(patternInfo.getGaugePattern())
			.needles(needleDtoList)
			.category(categoryDto)
			.thumbnails(thumbnailList)
			.build();

		return info;
	}

	public void setLevelVote(int patternId, int difficultyNum) throws Exception {
		Optional<String> memberId = SecurityUtil.getCurrentAuthentication();
		Optional<Member> member = memberRepository.findById(Long.parseLong(memberId.get()));

		Optional<Pattern> pattern = patternRepository.findByPatternId(patternId);

		//난이도 테이블에 투표 정보 저장
		DifficultyVote vote = DifficultyVote.builder()
			.member(member.get())
			.pattern(pattern.get())
			.score(difficultyNum)
			.build();
		difficultyVoteRepository.save(vote);

		//pattern 테이블도 갱신해줌
		Pattern updatePattern = pattern.get();

		int sum = updatePattern.getDifficultySum() + difficultyNum;
		int cnt = updatePattern.getDifficultyCnt() + 1;
		Float avg = (float)(sum / cnt);

		updatePattern.setDifficultyAvg(avg);
		updatePattern.setDifficultyCnt(cnt);
		updatePattern.setDifficultySum(sum);

		Pattern savedPattern = patternRepository.save(updatePattern);
		if (savedPattern == null) {
			throw new Exception("Failed");
		}

	}

	@Transactional
	public void insertPattern(PatternDto patternDto, List<PatternThumbnailDto> thumbnail, String userId) throws
		Exception {

		//입력한 가장 하위 카데고리에 해당하는 Category id를 찾기
		Optional<Category> category = categoryRepository.findByCategoryName(patternDto.getCategory());

		//해당 userId에 해당하는 member를 찾음
		Optional<String> memberId = SecurityUtil.getCurrentAuthentication();
		Optional<Member> member = memberRepository.findById(Long.parseLong(memberId.get()));
		//Optional<Member> member = memberRepository.findById((long)(10));

		System.out.println(patternDto);
		System.out.println(thumbnail.toString());

		if (!category.isPresent()) {
			throw new Exception();
		}
		if (!member.isPresent()) {
			throw new Exception();
		}

		//도안 테이블 삽입
		Pattern pattern = Pattern.builder()
			.gaugePattern(patternDto.getGuagePattern())
			.gauge(patternDto.getGauge())
			.gaugeDivisor(patternDto.getGaugeDivisor())
			.notesHtml(patternDto.getNotesHtml())
			.rowGauge(patternDto.getRowGauge())
			.name(patternDto.getPatternName())
			.yardage(patternDto.getYardage())
			.yardageMax(patternDto.getYardageMax())
			.yardageDescription(patternDto.getYarnWeightDescription())
			.category(category.get())
			.member(member.get())
			.build();

		System.out.println(pattern.toString());

		//도안 썸네일 테이블에 삽입
		for (PatternThumbnailDto dto : thumbnail) {
			PatternThumbnail request = PatternThumbnail.builder()
				.mainImg(dto.getMainImg())
				.thumbImg(dto.getThumbImg())
				.pattern(pattern)
				.build();
			patternThumbnailRepository.save(request);

			pattern.addPatternThumbnail(request);//PatternThumbnail 객체를 Pattern 객체의 patternThumbnails 리스트에 추가
		}

		//바늘 테이블에 삽입
		Needle needle = Needle.builder().metric(patternDto.getMetric()).build();
		pattern.addNeedle(needle);

		patternRepository.save(pattern);

	}

	public void setPatternLike(int patternId) throws Exception {
		Optional<String> memberId = SecurityUtil.getCurrentAuthentication();
		Optional<Member> member = memberRepository.findById(Long.parseLong(memberId.get()));
		Optional<Pattern> pattern = patternRepository.findByPatternId(patternId);

		if (!member.isPresent() || !pattern.isPresent()) {
			throw new Exception();
		}

		Pattern getPattern = pattern.get();
		Member getMember = member.get();

		PatternLike patternLike = PatternLike.builder()
			.pattern(getPattern)
			.member(getMember)
			.build();

		patternLikeRepository.save(patternLike);
	}

}
