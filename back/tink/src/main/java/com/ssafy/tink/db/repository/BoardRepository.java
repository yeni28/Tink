package com.ssafy.tink.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.tink.db.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{


	Optional<Board> findByBoardId(int boardId);
	Optional<Board> findByBoardIdAndBoardCategory(int boardId, String boardCategory);

	// 질문글, 소모임글-최신순
	@Modifying
	@Query("select b.boardId as boardId, b.title as title, b.content as content, b.hit as hit, b.comments.size as commentCnt "
		+ "from Board b "
		+ "where b.boardCategory = :cate "
		+ "order by b.boardId desc")
	Optional<List<QnaGroupInfoInterface>> findBoardAllByBoardCategoryOrderByBoardIdDesc(@Param(("cate"))String boardCategory);

	// 질문글, 소모임글-오랜된순
	@Modifying
	@Query("select b.boardId as boardId, b.title as title, b.content as content, b.hit as hit, b.comments.size as commentCnt "
		+ "from Board b "
		+ "where b.boardCategory = :cate "
		+ "order by b.boardId asc")
	Optional<List<QnaGroupInfoInterface>> findBoardAllByBoardCategoryOrderByBoardIdAsc(@Param(("cate"))String boardCategory);

	// 질문글, 소모임글-인기순
	@Modifying
	@Query("select b.boardId as boardId, b.title as title, b.content as content, b.hit as hit, b.comments.size as commentCnt "
		+ "from Board b "
		+ "where b.boardCategory = :cate "
		+ "order by b.boardId asc")
	Optional<List<QnaGroupInfoInterface>> findBoardAllByBoardCategoryOrderByHitDesc(@Param(("cate"))String boardCategory);


	// 자랑글-최신순
	@Modifying
	@Query("select b.boardId as boardId, b.title as title, b.member.memberId as memberId, b.hit as hit, b.liked as liked, "
		+ "p.thumbImg as patternThumbnail "
		+ "from Board b "
		+ "left join PatternThumbnail p "
		+ "on b.pattern.patternId = p.pattern.patternId "
		+ "where b.boardCategory = :cate "
		+ "order by b.hit desc")
	Optional<List<ReviewInfoInterface>> findReviewAllByBoardCategoryOrderByBoardIdDesc(@Param(("cate"))String boardCategory);

	// 자랑글- 오랜된순
	@Modifying
	@Query("select b.boardId as boardId, b.title as title, b.member.memberId as memberId, b.hit as hit, b.liked as liked, "
		+ "p.thumbImg as patternThumbnail "
		+ "from Board b "
		+ "left join PatternThumbnail p "
		+ "on b.pattern.patternId = p.pattern.patternId "
		+ "where b.boardCategory = :cate "
		+ "order by b.hit desc")
	Optional<List<ReviewInfoInterface>> findReviewAllByBoardCategoryOrderByBoardIdAsc(@Param(("cate"))String boardCategory);

	// 자랑글- 인기순
	@Modifying
	@Query("select b.boardId as boardId, b.title as title, b.member.memberId as memberId, b.hit as hit, b.liked as liked, "
		+ "p.thumbImg as patternThumbnail "
		+ "from Board b "
		+ "left join PatternThumbnail p "
		+ "on b.pattern.patternId = p.pattern.patternId "
		+ "where b.boardCategory = :cate "
		+ "order by b.hit desc")
	Optional<List<ReviewInfoInterface>> findReviewAllByBoardCategoryOrderByHitDesc(@Param(("cate"))String boardCategory);

	void deleteByBoardId(int boardId);

	// 삽입, 수정, 삭제 쿼리 사용시 필요한 어노테이션
	@Modifying
	@Query("update Board b "
		+ "set b.hit = b.hit + 1 "
		+ "where b.boardId = :id")
	int updateView(@Param(("id")) int id);

}
