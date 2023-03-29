package com.ssafy.tink.db.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.sun.istack.NotNull;
import lombok.Getter;

@Entity
@Getter
public class Keyword {

	@Id
	@Column(name = "keyword_id")
	private int keywordId;

	@Column(length = 50)
	private String name;

	@ManyToMany(mappedBy = "keywords")
	private List<Pattern> patterns = new ArrayList<>();
}
