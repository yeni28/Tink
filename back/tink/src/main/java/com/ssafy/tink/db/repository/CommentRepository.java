package com.ssafy.tink.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.tink.db.entity.Board;
import com.ssafy.tink.db.entity.Comment;
import com.ssafy.tink.dto.CommentInfoDto;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

	long countByBoard(Board board);

	List<Comment> findByBoard(Board board);

	List<Comment> findAllByBoard(Board board);

	Optional<Comment> findByCommentId(int commentId);

	Object deleteByCommentId(int commentId);

	// 해당 게시글의 댓글 목록
	@Modifying
	@Query(
		nativeQuery = true,
		value = "select c.comment_id, c.created_date, c.content, m.nickname, t.thumb_img "
		+ "from comment c "
		+ "left join Member m "
		+ "on c.member_id = m.member_id "
		+ "left join Thumbnail t "
		+ "on t.thumbnail_id = m.image_id "
		+ "where c.board_id= :id")
	List<CommentInfoInterface> findAllByBoardNative(@Param(("id")) int boardId);
}
