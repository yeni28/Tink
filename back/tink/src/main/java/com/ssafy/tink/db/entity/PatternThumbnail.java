package com.ssafy.tink.db.entity;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pattern_thumbnail")
public class PatternThumbnail {

	@Id
	@Column(name = "pattern_thumbnail_id")
	private int patternThumbnailId;

	@NotNull
	private String main_img;

	@NotNull
	private String thumb_img;

	@ManyToOne
	@JoinColumn(name = "pattern_id", referencedColumnName = "pattern_id", nullable = false)
	@NotNull
	private Pattern pattern;

}
