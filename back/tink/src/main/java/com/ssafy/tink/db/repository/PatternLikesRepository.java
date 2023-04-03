package com.ssafy.tink.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.tink.db.entity.PatternLikeId;
import com.ssafy.tink.db.entity.PatternLikes;

@Repository
public interface PatternLikesRepository extends JpaRepository<PatternLikes, PatternLikeId> {

	@Query(value = "select * from pattern_likes p where p.pattern_id=:patternId and p.member_id=:memberId", nativeQuery = true)
	Optional<PatternLikes> searchPatternLike(@Param("patternId") int patterId, @Param("memberId") Long memberId);


}
