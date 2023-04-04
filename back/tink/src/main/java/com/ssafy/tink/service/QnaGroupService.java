package com.ssafy.tink.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.tink.config.Util.SecurityUtil;
import com.ssafy.tink.db.entity.Board;
import com.ssafy.tink.db.entity.Comment;
import com.ssafy.tink.db.entity.JarangLikes;
import com.ssafy.tink.db.entity.Member;
import com.ssafy.tink.db.repository.BoardRepository;
import com.ssafy.tink.db.repository.CommentInfoInterface;
import com.ssafy.tink.db.repository.CommentRepository;
import com.ssafy.tink.db.repository.FollowRepository;
import com.ssafy.tink.db.repository.JarangLikesRepository;
import com.ssafy.tink.db.repository.MemberRepository;
import com.ssafy.tink.db.repository.QnaGroupInfoInterface;
import com.ssafy.tink.db.repository.ThumbnailRepository;
import com.ssafy.tink.dto.QnaGroupInfoDto;
import com.ssafy.tink.dto.QnaGroupInputDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class QnaGroupService {

	@Autowired
	private BoardRepository boardRepository;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private FollowRepository followRepository;
	@Autowired
	private JarangLikesRepository jarangLikesRepository;
	@Autowired
	private ThumbnailRepository thumbnailRepository;

	@Transactional
	public Object create(QnaGroupInputDto community) {

		Optional<String> memberId = SecurityUtil.getCurrentAuthentication();

		Board boardInfo = Board.builder()
			.title(community.getTitle())
			.content(community.getContent())
			.member(Member.builder()
				.memberId(Long.parseLong(memberId.get()))
				.build())
			.boardCategory(community.getBoardCategory())
			.build();

		return boardRepository.save(boardInfo);
	}

	@Transactional
	public Object update(QnaGroupInputDto community) {

		Optional<Board> board = boardRepository.findByBoardId(community.getBoardId());

		if( !board.isPresent() ) {
			return Optional.empty();
		}

		board.get().updateBoard(community.getTitle(), community.getContent());

		return board;
	}

	@Transactional
	public Optional<QnaGroupInfoDto> getReview(int boardId) {
		Optional<Board> review = boardRepository.findByBoardId(boardId);
		log.debug("조회결과 : " + review.toString());
		if( !review.isPresent() ) {
			return Optional.empty();
		}

		return createInfoByQnaGroup(review.get());
	}

	@Transactional
	Optional<QnaGroupInfoDto> createInfoByQnaGroup(Board board) {

		Optional<String> memberId = SecurityUtil.getCurrentAuthentication();
		Optional<Member> fromMember = memberRepository.findById(Long.parseLong(memberId.get()));

		long commentCnt = commentRepository.countByBoard(board);
		List<CommentInfoInterface> comments;
		comments = commentRepository.findAllByBoardNative(board.getBoardId());

		int toId = Long.valueOf(board.getMember().getMemberId()).intValue();

		boolean isFollowed = false;
		boolean isLiked = false;
		if ( !fromMember.get().getFollows().isEmpty() ){
			isFollowed = followRepository.findByMemberAndToId(fromMember.get(), toId);
			// isLiked = jarangLikesRepository.findByBoardAndMember(board, fromMember.get());
		}

		long cntLikes = jarangLikesRepository.countJarangLikesByBoard(board);

		QnaGroupInfoDto Info = QnaGroupInfoDto.builder()
			.boardId(board.getBoardId())
			.title(board.getTitle())
			.content(board.getContent())
			.createdDate(board.getCreatedDate())
			.updatedDate(board.getUpdatedDate())
			.liked((int)cntLikes)
			.hit(board.getHit())
			.nickname(fromMember.get().getNickname())
			.thumbnail(thumbnailRepository.findByIdNative(memberId).get().getThumbImg())
			.boardCategory(board.getBoardCategory())
			.commentCnt(Long.valueOf(commentCnt).intValue())
			.comments(comments)
			.isFollowed(isFollowed)
			.isLiked(isLiked)
			.build();

		log.debug("자랑글 디테일 결과 : " + Info.toString());
		return Optional.ofNullable(Info);
	}

	@Transactional
	public List<QnaGroupInfoInterface> getBoardList(String filter, String boardCategory) {

		List<QnaGroupInfoInterface> boardList = new ArrayList<>();

		if(filter.equals("최신순")) {
			boardList = boardRepository.findBoardAllByBoardCategoryOrderByBoardIdDesc(boardCategory).orElseThrow(() -> {
				return new IllegalArgumentException("게시글 목록 정보를 찾을 수 없습니다.");
			});
		}else if(filter.equals("인기순")){
			boardList = boardRepository.findBoardAllByBoardCategoryOrderByHitDesc(boardCategory).orElseThrow(()->{
				return new IllegalArgumentException("게시글 목록 정보를 찾을 수 없습니다.");
			});
		}else{
			boardList = boardRepository.findBoardAllByBoardCategoryOrderByBoardIdAsc(boardCategory).orElseThrow(()->{
				return new IllegalArgumentException("게시글 목록 정보를 찾을 수 없습니다.");
			});
		}

		return boardList;
	}

	@Transactional
	public Object delete(int boardId) {
		Optional<Board> board = boardRepository.findByBoardId(boardId);
		if( !board.isPresent() ) {
			return Optional.empty();
		}

		// 댓글 삭제
		List<Comment> commentList = commentRepository.findAllByBoard(board.get());
		for(Comment comment : commentList) {
			commentRepository.delete(comment);
		}

		// 좋아요 삭제
		List<JarangLikes> likesList = jarangLikesRepository.findAllByBoard(board.get());
		for(JarangLikes like : likesList) {
			jarangLikesRepository.delete(like);
		}

		boardRepository.deleteByBoardId(boardId);
		return null;
	}

	@Transactional
	public int updateView(int id){
		return boardRepository.updateView(id);
	}


}
