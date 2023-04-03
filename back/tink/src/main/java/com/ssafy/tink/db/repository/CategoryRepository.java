package com.ssafy.tink.db.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.tink.db.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	Optional<Category> findByCategoryName(String categoryName);

	Optional<Category> findByCategoryId(int categoryId);

}
