package com.ssafy.tink.db.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.tink.db.entity.Pattern;
@Repository
public interface PatternRepository extends JpaRepository<Pattern, Integer> {

	Optional<Pattern> findByPatternId(int patternId);

}
