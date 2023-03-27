package com.ssafy.tink.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.tink.db.entity.Thumbnail;

public interface ThumbnailRepository extends JpaRepository<Thumbnail, Integer> {
}
