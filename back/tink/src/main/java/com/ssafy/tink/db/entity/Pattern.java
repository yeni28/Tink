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

import com.sun.istack.NotNull;

import org.hibernate.annotations.ColumnDefault;
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

	@Column(length = 200)
	private String name;

	@Column(name = "difficulty_sum")
	@ColumnDefault("0")
	private int difficultySum;

	@Column(name = "difficulty_cnt")
	@ColumnDefault("0")
	private int difficultyCnt;

	@Column(name = "difficulty_avg")
	@ColumnDefault("0")
	private Float difficultyAvg;

	@Column(name = "download_url")
	private String downloadUrl;

	private Float gauge;

	@Column(name = "gauge_divisor")
	private String gaugeDivisor;

	@Column(name = "gauge_pattern")
	private String gaugePattern;

	@Column(name = "row_gauge")
	private Float rowGauge;

	private int yardage;

	@Column(name = "yardage_max")
	private int yardageMax;

	@Column(name = "sizes_available", length = 50)
	private String sizesAvailable;

	@Column(name = "notes_html", length = 500)
	private String notesHtml;

	@Column(name = "yarn_weight_description", length = 25)
	private String yarnWeightDescription;

	@Column(name = "yardage_description", length = 50)
	private String yardageDescription;

	@OneToMany
	@JoinTable(
		name = "PATTERN_NEEDLE",
		joinColumns = @JoinColumn(name = "pattern_id", referencedColumnName = "pattern_id", nullable = false),
		inverseJoinColumns = @JoinColumn(name = "needle_id", referencedColumnName = "needle_id",  nullable = false)
	)
	private List<Needle> needles = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false)
	@NotNull
	private Category category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", referencedColumnName = "member_id", nullable = false)
	@NotNull
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


	public void setName(String name) {
		this.name = name;
	}


	public void setCategory(Category category) {
		this.category = category;
	}

	public void setMember(Member member) {
		this.member = member;
	}
}
