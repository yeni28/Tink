package com.ssafy.tink.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.tink.db.dsl.MemberQueryDslRepository;
import com.ssafy.tink.db.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>, MemberQueryDslRepository {

	Optional<Member> findByEmail(String email);
	@Override
	@EntityGraph(attributePaths = {"follows","thumbnail"})
	Optional<Member> findById(Long memberId);

	@EntityGraph(attributePaths = {"boards","patterns"})
	Optional<Member> findTop5ById(Long memberId);

}
