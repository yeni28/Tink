package com.ssafy.tink.db.repository;


import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.tink.db.dsl.MemberQueryDslRepository;
import com.ssafy.tink.db.entity.Pattern;
import com.ssafy.tink.db.entity.PatternThumbnail;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface PatternThumbnailRepository extends JpaRepository<PatternThumbnail, Integer> {

	@Query(nativeQuery = true, value = "select * from pattern_thumbnail as t where t.pattern_id=:patternId")
	List<PatternThumbnail> findByPatternId(@Param("patternId") int patternId);

	@Query(nativeQuery = true, value = "select thumb_img from pattern_thumbnail as t where t.pattern_id=:patternId limit 1")
	String findByPatternIdNative(@Param("patternId") int patternId);


}
