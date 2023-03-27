package com.ssafy.tink.db.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;

@Entity
@DynamicUpdate
@DynamicInsert
@Getter
public class Pattern extends BaseEntity {

	@Id
	@Column(name = "pattern_id")
	private int patternId;

	private String name;

	@Column(name = "difficulty_sum")
	private int difficultySum;

	@Column(name = "difficulty_cnt")
	private int difficultyCnt;

	@Column(name = "download_url")
	private String downloadUrl;

	private Float gauge;

	@Column(name = "gauge_divisor")
	private Float gaugeDivisor;

	@Column(name = "gauge_pattern")
	private String gaugePattern;

	@Column(name = "row_gauge")
	private Float rowGauge;

	private int yardage;

	@Column(name = "yardage_max")
	private int yardageMax;

	@Column(name = "sizes_available")
	private String sizesAvailable;

	@Column(name = "notes_html")
	private String notesHtml;

	@Column(name = "yarn_weight_description")
	private String yarnWeightDescription;

	@Column(name = "yardage_description")
	private String yardageDescription;

	@OneToMany
	@JoinTable(
		name = "PATTERN_NEEDLE",
		joinColumns = @JoinColumn(name = "pattern_id", referencedColumnName = "pattern_id"),
		inverseJoinColumns = @JoinColumn(name = "needle_id", referencedColumnName = "needle_id")
	)
	private List<Needle> needles = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", referencedColumnName = "category_id")
	private Category category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", referencedColumnName = "member_id")
	private Member member;

	@OneToMany
	@JoinTable(
		name = "PATTERN_LIKES",
		joinColumns = @JoinColumn(name = "pattern_id", referencedColumnName = "pattern_id"),
		inverseJoinColumns = @JoinColumn(name = "member_id", referencedColumnName = "member_id")
	)
	private List<Member> patternLikes = new ArrayList<>();

	@ManyToMany
	@JoinTable(
		name = "PATTERN_KEYWORD",
		joinColumns = @JoinColumn(name = "pattern_id", referencedColumnName = "pattern_id"),
		inverseJoinColumns = @JoinColumn(name = "keyword_id", referencedColumnName = "keyword_id")
	)
	private List<Keyword> keywords = new ArrayList<>();

	@OneToMany(mappedBy = "pattern")
	private List<PatternThumbnail> patternThumbnails;

	@OneToMany(mappedBy = "pattern")
	private List<Pack> packs = new ArrayList<>();

}
