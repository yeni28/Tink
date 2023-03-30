package com.ssafy.tink.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.tink.db.entity.PatternLikeId;
import com.ssafy.tink.db.entity.PatternLikes;

@Repository
public interface PatternLikesRepository extends JpaRepository<PatternLikes, PatternLikeId> {
}
