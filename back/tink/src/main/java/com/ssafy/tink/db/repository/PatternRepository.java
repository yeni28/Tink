package com.ssafy.tink.db.repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.tink.db.dsl.PatternQueryDslRepository;
import com.ssafy.tink.db.entity.Pattern;

@Repository
public interface PatternRepository extends JpaRepository<Pattern, Integer>,
	PatternQueryDslRepository {

	//추천 시스템을 위한 쿼리
	@Query("SELECT p FROM Pattern p")
	List<Pattern> findAllPattern();

	@EntityGraph(attributePaths = {"category", "needles"})
	Optional<Pattern> findByPatternId(int patternId);

	//검색 조건을 바탕으로 도안 리스트 출력
	@Query("SELECT p FROM Pattern p JOIN p.member m WHERE p.name LIKE %:keyword% OR m.nickname LIKE %:keyword%")
	Page<Pattern> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

	@Query("SELECT p FROM Pattern p")
	Page<Pattern> searchAll(Pageable pageable);

	// @Query("SELECT p FROM Pattern p WHERE p.patternId IN (SELECT pl.pattern.patternId FROM PatternLike pl GROUP BY pl.pattern.patternId ORDER BY COUNT(pl.pattern.patternId) DESC)")
	// List<Pattern> findPatternsOrderByLikes(Pageable pageable);

	@Query(value = "SELECT * FROM Pattern p INNER JOIN (SELECT pattern_id, COUNT(*) AS like_count FROM pattern_likes GROUP BY pattern_id ORDER BY like_count DESC ) pl ON p.pattern_id = pl.pattern_id where p.name like %:keyword% ORDER BY pl.like_count DESC, p.name ASC", nativeQuery = true)
	List<Pattern> findKeywordAndOrderByLikes(@Param("keyword") String keyword, Pageable pageable);

	@Query(value = "SELECT * FROM Pattern p INNER JOIN (SELECT pattern_id, COUNT(*) AS like_count FROM pattern_likes GROUP BY pattern_id ORDER BY like_count DESC ) pl ON p.pattern_id = pl.pattern_id ORDER BY pl.like_count DESC, p.name ASC", nativeQuery = true)
	List<Pattern> findOrderByLikes(Pageable pageable);

	@EntityGraph(attributePaths = {"category", "needles"})
	@Query(value = "SELECT * FROM Pattern p WHERE p.pattern_id=:patternId and p.member_id=:memberId", nativeQuery = true)
	Optional<Pattern> findByPattern(@Param("patternId") int patternId, @Param("memberId") Long memberId);

	@Query(value = "select * from pattern p inner join "
		+ "(select pattern_id, count(*) as like_count from pattern_likes pl "
		+ "WHERE pl.created_date >= :createdDate AND pl.created_date < :endDate group by pattern_id order by like_count desc) pl "
		+ "on p.pattern_id = pl.pattern_id", nativeQuery = true)
	List<Pattern> findWeeklyBest(@Param("createdDate") Timestamp createdDate, @Param("endDate") Timestamp endDate);

	Optional<List<Pattern>> findAllByNameContainingOrderByName(String name);


	@Query(value="select * from pattern join pattern_likes on pattern.pattern_id = pattern_likes.pattern_id where pattern_likes.member_id =:memberId  and pattern.pattern_id =:patternId ", nativeQuery = true)
	Optional<Pattern> searchPatternLikes(@Param("patternId") int patternId, @Param("memberId") Long memberId);


}
