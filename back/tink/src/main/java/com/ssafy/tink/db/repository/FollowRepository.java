package com.ssafy.tink.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.tink.db.entity.Follow;

public interface FollowRepository extends JpaRepository<Follow, Integer> {
}
