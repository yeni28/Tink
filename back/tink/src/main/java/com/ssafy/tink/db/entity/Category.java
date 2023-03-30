package com.ssafy.tink.db.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;

import com.sun.istack.NotNull;
import lombok.Getter;

@Entity
@Getter
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private int categoryId;

	@Column(name = "category_name")
	@NotNull
	private String categoryName;

	@NotNull
	@ColumnDefault("0")
	private int depth;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "parent_id", referencedColumnName = "category_id", nullable = false)
	private Category parent;

	@OneToMany(mappedBy = "category")
	private List<Pattern> patterns = new ArrayList<>();
	
	public void addPattern(Pattern pattern) {
		pattern.setCategory(this);
		patterns.add(pattern);
	}

}
