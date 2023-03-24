package com.ssafy.tink.db.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;

@Entity
@Getter
public class Category {

	@Id
	@Column(name = "category_id")
	private int categoryId;

	@Column(name = "category_name")
	private String categoryName;

	private int depth;

	@ManyToOne
	@JoinColumn(name = "parent_id", referencedColumnName = "category_id")
	private Category parent;

	@OneToMany(mappedBy = "category")
	private List<Pattern> patterns = new ArrayList<>();
	
	public void addPattern(Pattern pattern) {
		pattern.setCategory(this);
		patterns.add(pattern);
	}

}
