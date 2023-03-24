package com.ssafy.tink.db.entity;

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

	private String main_img;
	private String thumb_img;

	@ManyToOne
	@JoinColumn(name = "pattern_id", referencedColumnName = "pattern_id")
	private Pattern pattern;

}
