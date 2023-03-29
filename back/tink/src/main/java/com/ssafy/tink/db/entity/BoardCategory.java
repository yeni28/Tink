package com.ssafy.tink.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;
import lombok.Getter;

@Entity
@Table(name = "board_category")
@Getter
public class BoardCategory {

	@Id
	@Column(name = "board_category_id")
	private int boardCategoryId;

	@Column(name = "category_name", length = 50)
	@NotNull
	private String categoryName;

}
