package com.ssafy.tink.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.tink.config.Util.SecurityUtil;
import com.ssafy.tink.db.entity.Board;
import com.ssafy.tink.db.entity.Comment;
import com.ssafy.tink.db.entity.Member;
import com.ssafy.tink.db.repository.BoardRepository;
import com.ssafy.tink.db.repository.CommentRepository;
import com.ssafy.tink.db.repository.MemberRepository;
import com.ssafy.tink.dto.CommentInputDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private CommentRepository commentRepository;

	@Transactional
	public Object create(CommentInputDto comment) {

		Optional<Board> board = boardRepository.findByBoardId(comment.getBoardId());
		if( !board.isPresent() ) {
			return Optional.empty();
		}

		Optional<String> memberId = SecurityUtil.getCurrentAuthentication();
		Optional<Member> member = memberRepository.findById(Long.parseLong(memberId.get()));

		Comment commentInfo = Comment.builder()
			.content(comment.getContent())
			.board(board.get())
			.member(member.get())
			.build();

		return commentRepository.save(commentInfo);
	}


	@Transactional
	public Object delete(int commentId) {

		return commentRepository.deleteByCommentId(commentId);
	}

	public Optional<Comment> findByCommentId(int commentId) {
		return commentRepository.findByCommentId(commentId);
	}

	public Optional<Board> findByBoardId(int boardId) {
		return boardRepository.findByBoardId(boardId);
	}
}
