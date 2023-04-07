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

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;

@Entity
@Getter
@Builder
@AllArgsConstructor
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private int categoryId;

	@Column(name = "category_name")
//	@NotNull
	private String categoryName;

//	@NotNull
	@ColumnDefault("0")
	private int depth;

	@ManyToOne
	@JoinColumn(name = "parent_id", referencedColumnName = "category_id")
	private Category parent;

	@OneToMany(mappedBy = "category")
	@JsonBackReference
	private List<Pattern> patterns = new ArrayList<>();

	public void addPattern(Pattern pattern) {
		pattern.setCategory(this);
		patterns.add(pattern);
	}

}
