package com.ssafy.tink.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;

@Getter
@ApiModel("CategoryDto")
public class CategoryDto {

	private int id;
	private String categoryName;
	private int depth;
	private CategoryDto parentCategory;

	@Builder
	public CategoryDto(int id, String categoryName, int depth, CategoryDto parentCategory) {
		this.id = id;
		this.categoryName = categoryName;
		this.depth = depth;
		this.parentCategory = parentCategory;
	}
}
