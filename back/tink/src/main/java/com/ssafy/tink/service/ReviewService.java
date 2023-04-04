package com.ssafy.tink.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.tink.config.Util.SecurityUtil;
import com.ssafy.tink.config.ect.BadRequestException;
import com.ssafy.tink.db.entity.Board;
import com.ssafy.tink.db.entity.Comment;
import com.ssafy.tink.db.entity.JarangLikes;
import com.ssafy.tink.db.entity.Material;
import com.ssafy.tink.db.entity.Member;
import com.ssafy.tink.db.entity.Pattern;
import com.ssafy.tink.db.entity.Thumbnail;
import com.ssafy.tink.db.repository.BoardRepository;
import com.ssafy.tink.db.repository.CommentInfoInterface;
import com.ssafy.tink.db.repository.CommentRepository;
import com.ssafy.tink.db.repository.FollowRepository;
import com.ssafy.tink.db.repository.JarangLikesRepository;
import com.ssafy.tink.db.repository.MaterialRepository;
import com.ssafy.tink.db.repository.MemberRepository;
import com.ssafy.tink.db.repository.PatternRepository;
import com.ssafy.tink.db.repository.ReviewInfoInterface;
import com.ssafy.tink.db.repository.ThumbnailRepository;
import com.ssafy.tink.dto.PatternInfoDto;
import com.ssafy.tink.dto.PatternListDto;
import com.ssafy.tink.dto.ReviewInfoDto;
import com.ssafy.tink.dto.ReviewInputDto;
import com.ssafy.tink.dto.ThumbnailDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReviewService {

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
	private PatternRepository patternRepository;

	@Autowired
	private MaterialRepository materialRepository;
	@Autowired
	private ThumbnailRepository thumbnailRepository;

	@Transactional
	public Object create(ReviewInputDto community, Thumbnail thumbnail) {

		if(!community.getBoardCategory().equals("review"))
			return new IllegalArgumentException("커뮤니티 카테고리가 잘못되었습니다.");

		Optional<String> memberId = SecurityUtil.getCurrentAuthentication();
		Optional<Member> member = memberRepository.findById(Long.parseLong(memberId.get()));
		Optional<Pattern> pattern = patternRepository.findById(community.getPatternId());

		Pattern p = new Pattern();
		p.setPatternId(community.getPatternId());

		Board boardInfo = Board.builder()
			.title(community.getTitle())
			.content(community.getContent())
			.member(Member.builder()
				.memberId(Long.parseLong(memberId.get()))
				.build())
			.boardCategory(community.getBoardCategory())
			.pattern(p)
			.thumbnail(Thumbnail.builder()
				.thumbImg(thumbnail.getThumbImg())
				.mainImg(thumbnail.getMainImg())
				.build())
			.build();

		Material materialInfo = Material.builder()
			.yarnName(community.getYarnName())
			.yarnWeight(community.getYarnWeight())
			.yarnLength(community.getYarnLength())
			.needle(community.getNeedle())
			.time(community.getTime())
			.board(boardInfo)
			.build();

		materialRepository.save(materialInfo);

		return boardRepository.save(boardInfo);
	}

	@Transactional
	public Object update(ReviewInfoDto community) {

		Optional<Board> board = boardRepository.findByBoardIdAndBoardCategory(community.getBoardId(), "review");
		if(board == null){
			throw new BadRequestException("해당 글을 찾을 수 없습니다.");
		}

		Optional<String> memberId = SecurityUtil.getCurrentAuthentication();
		Optional<Member> member = memberRepository.findById(Long.parseLong(memberId.get()));
		Optional<Pattern> pattern = patternRepository.findById(community.getPatternId());

		// board.get().update(community.getTitle(), community.getContent(), materialInfo);
		board.get().updateBoard(community.getTitle(), community.getContent());

		return board;
	}

	@Transactional
	public Optional<ReviewInfoDto> getReview(int boardId) {
		Optional<Board> review = boardRepository.findByBoardId(boardId);
		log.debug("자랑글 조회결과 : " + review.toString());
		if( !review.isPresent() ) {
			return Optional.empty();
		}

		return createInfoByReview(review.get());
	}

	private Optional<ReviewInfoDto> createInfoByReview(Board review) {

		Optional<String> memberId = SecurityUtil.getCurrentAuthentication();
		Optional<Member> fromMember = memberRepository.findById(Long.parseLong(memberId.get()));

		long commentCnt = commentRepository.countByBoard(review);
		List<CommentInfoInterface> comments;
		comments = commentRepository.findAllByBoardNative(review.getBoardId());

		int toId = Long.valueOf(review.getMember().getMemberId()).intValue();

		boolean isFollowed = false;
		boolean isLiked = false;
		if ( !fromMember.get().getFollows().isEmpty() ){
			isFollowed = followRepository.findByMemberAndToId(fromMember.get(), toId);
		}

		Optional<JarangLikes> likes = jarangLikesRepository.findByBoardAndMember(review, fromMember.get());
		if(likes.isPresent()){
			isLiked = true;
		}

		long cntLikes = jarangLikesRepository.countJarangLikesByBoard(review);
		Optional<Material> material = materialRepository.findByBoard(review);

		ReviewInfoDto reviewInfo = ReviewInfoDto.builder()
			.boardId(review.getBoardId())
			.title(review.getTitle())
			.content(review.getContent())
			.createdDate(String.valueOf(review.getCreatedDate()))
			.updatedDate(String.valueOf(review.getUpdatedDate()))
			.liked((int)cntLikes)
			.hit(review.getHit())
			.nickname(fromMember.get().getNickname())
			.thumbnail(thumbnailRepository.findByIdNative(memberId).get().getThumbImg())
			.patternId(review.getPattern().getPatternId())
			.patternThumbnail("")										// 경로 추가
			.boardCategory(review.getBoardCategory())
			.yarnName(material.get().getYarnName())
			.yarnWeight(material.get().getYarnWeight())
			.yarnLength(material.get().getYarnLength())
			.needle(material.get().getNeedle())
			.time(material.get().getTime())
			.commentCnt(Long.valueOf(commentCnt).intValue())
			.comments(comments)
			.isFollowed(isFollowed)
			.isLiked(isLiked)
			.build();

		log.debug("자랑글 디테일 결과 : " + reviewInfo.toString());
		return Optional.ofNullable(reviewInfo);
	}

	@Transactional
	public List<ReviewInfoInterface> getBoardList(String filter, String boardCategory) {

		List<ReviewInfoInterface> reviewList = new ArrayList<>();

		if(filter.equals("최신순")){
			reviewList = boardRepository.findReviewAllByBoardCategoryOrderByBoardIdDesc(boardCategory).orElseThrow(()->{
				return new IllegalArgumentException("게시글 목록 정보를 찾을 수 없습니다.");
			});
		}else if(filter.equals("인기순")){
			reviewList = boardRepository.findReviewAllByBoardCategoryOrderByHitDesc(boardCategory).orElseThrow(()->{
				return new IllegalArgumentException("게시글 목록 정보를 찾을 수 없습니다.");
			});
		}else{
			reviewList = boardRepository.findReviewAllByBoardCategoryOrderByBoardIdAsc(boardCategory).orElseThrow(()->{
				return new IllegalArgumentException("게시글 목록 정보를 찾을 수 없습니다.");
			});
		}

		return reviewList;
	}

	@Transactional
	public List<PatternListDto> getPatternList(String filter) {

		List<Pattern> list;
		List<PatternListDto> patternList = new ArrayList<>();

		list = patternRepository.findAllByNameContainingOrderByName(filter).orElseThrow(()->{
			return new IllegalArgumentException("해당 검색어가 포함되는 도안 목록 정보를 찾을 수 없습니다.");
		});

		for(int i=0; i<list.size(); i++){
			Pattern p = list.get(i);
			patternList.add(new PatternListDto(p.getPatternId(), p.getName()));
		}

		return patternList;
	}

	@Transactional
	public Object delete(int boardId) {
		Optional<Board> board = boardRepository.findByBoardId(boardId);

		if( !board.isPresent() ) {
			return Optional.empty();
		}

		// 댓글 삭제
		List<Comment> commentList = commentRepository.findByBoard(board.get());
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
	public Object like(int boardId){
		Optional<String> memberId = SecurityUtil.getCurrentAuthentication();
		Optional<Member> member = memberRepository.findById(Long.parseLong(memberId.get()));
		if( !member.isPresent() ) {
			return Optional.empty();
		}

		Optional<Board> board = boardRepository.findByBoardId(boardId);
		if( !board.isPresent() ) {
			return Optional.empty();
		}

		Optional<JarangLikes> likes = jarangLikesRepository.findByBoardAndMember(board.get(), member.get());
		if( !likes.isPresent()) {

			// JarangLikes likeInfo = JarangLikes.builder()
			// 	.board(board.get())
			// 	.member(member.get())
			// 	.build();

			JarangLikes likeInfo = JarangLikes.builder()
				.board(Board.builder()
					.boardId(boardId)
					.build())
				.member(Member.builder()
					.memberId(Long.parseLong(memberId.get()))
					.build())
				.build();

			return jarangLikesRepository.save(likeInfo);
		}
		else{
			jarangLikesRepository.deleteByBoardAndMember(board.get(), member.get());
			return "정상적으로 좋아요 취소되었습니다.";
		}
	}

	@Transactional
	public int updateView(int id){
		return boardRepository.updateView(id);
	}


}
