package com.ssafy.tink.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.tink.db.dsl.MemberQueryDslRepository;
import com.ssafy.tink.db.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{


	Optional<Board> findByBoardId(int boardId);
	Optional<Board> findByBoardIdAndBoardCategory(int boardId, String boardCategory);

	// 최신순
	Optional<List<Board>> findBoardAllByBoardCategoryOrderByBoardIdDesc(String boardCategory);

	// 오랜된순
	Optional<List<Board>> findBoardAllByBoardCategoryOrderByBoardIdAsc(String boardCategory);

	// 인기순
	Optional<List<Board>> findBoardAllByBoardCategoryOrderByHitDesc(String boardCategory);

	void deleteByBoardId(int boardId);

	// 삽입, 수정, 삭제 쿼리 사용시 필요한 어노테이션
	@Modifying
	@Query("update Board b "
		+ "set b.hit = b.hit + 1 "
		+ "where b.boardId = :id")
	int updateView(@Param(("id")) int id);

}
