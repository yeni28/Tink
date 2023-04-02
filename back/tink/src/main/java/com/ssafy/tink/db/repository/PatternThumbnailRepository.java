package com.ssafy.tink.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.tink.db.entity.PatternThumbnail;

public interface PatternThumbnailRepository extends JpaRepository<PatternThumbnail, Integer> {

	@Query(nativeQuery = true, value = "select * from pattern_thumbnail as t where t.pattern_id=:patternId")
	List<PatternThumbnail> findByPatternId(@Param("patternId") int patternId);

}
