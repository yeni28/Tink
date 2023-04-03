package com.ssafy.tink.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.tink.db.entity.Follow;
import com.ssafy.tink.db.entity.Member;

public interface FollowRepository extends JpaRepository<Follow, Integer> {

	Boolean findByMemberAndToId(Member member, int toId);
}
