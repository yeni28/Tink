package com.ssafy.tink.db.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.tink.db.entity.Thumbnail;

@Repository
public interface ThumbnailRepository extends JpaRepository<Thumbnail, Integer> {


	@Query(value = "SELECT *  " +
		"FROM thumbnail " +
		"WHERE thumbnail_id = (SELECT m.image_id  " +
		"FROM member as m " +
		"WHERE m.member_id = :id ) "
		, nativeQuery = true)
	Optional<Thumbnail> findByIdNative(@Param("id") Optional<String> memberId);

	@Query(value = "select * "
		+ "from thumbnail "
		+ "where thumbnail_id = (select thumbnail_id "
		+ "from jarang_img "
		+ "where board_id = :id )"
		, nativeQuery = true)
	Optional<Thumbnail> findByBoardIdNative(@Param("id") int boardId);


}
