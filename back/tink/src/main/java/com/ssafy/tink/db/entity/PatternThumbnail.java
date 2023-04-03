package com.ssafy.tink.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pattern_thumbnail")
@Getter
@NoArgsConstructor
public class PatternThumbnail {

	@Id
	@Column(name = "pattern_thumbnail_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int patternThumbnailId;

	@NotNull
	@Column(name = "main_img")
	private String mainImg;

	@NotNull
	@Column(name = "thumb_img")
	private String thumbImg;

	@ManyToOne
	@JoinColumn(name = "pattern_id", referencedColumnName = "pattern_id", nullable = false)
	@NotNull
	@JsonManagedReference
	private Pattern pattern;

	@Builder
	public PatternThumbnail(int patternThumbnailId, String mainImg, String thumbImg, Pattern pattern) {
		this.patternThumbnailId = patternThumbnailId;
		this.mainImg = mainImg;
		this.thumbImg = thumbImg;
		this.pattern = pattern;
	}

	public PatternThumbnail(String mainImg, String thumbImg) {
		this.mainImg = mainImg;
		this.thumbImg = thumbImg;
	}

	public void setPattern(Pattern pattern) {
		this.pattern = pattern;
	}
}
