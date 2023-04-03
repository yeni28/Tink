package com.ssafy.tink.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.tink.db.dsl.MemberQueryDslRepository;
import com.ssafy.tink.db.entity.Board;
import com.ssafy.tink.db.entity.Member;
import com.ssafy.tink.db.entity.JarangLikes;

@Repository
public interface JarangLikesRepository extends JpaRepository<JarangLikes, Long>{

	Optional<JarangLikes> findByBoardAndMember(Board board, Member member);

	List<JarangLikes> findAllByBoard(Board board);

	Long countJarangLikesByBoard(Board board);

	@Override
	void delete(JarangLikes entity);

	void deleteByBoardAndMember(Board board, Member member);
}
