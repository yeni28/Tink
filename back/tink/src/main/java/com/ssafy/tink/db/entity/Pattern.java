package com.ssafy.tink.db.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@DynamicUpdate
@DynamicInsert
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Pattern extends BaseEntity {

	@ManyToMany
	@JoinTable(
		name = "PATTERN_KEYWORD",
		joinColumns = @JoinColumn(name = "pattern_id", referencedColumnName = "pattern_id"),
		inverseJoinColumns = @JoinColumn(name = "keyword_id", referencedColumnName = "keyword_id")
	)
	private final List<Keyword> keywords = new ArrayList<>();

	@OneToMany(mappedBy = "pattern", cascade = CascadeType.PERSIST,
		orphanRemoval = true)
	private final List<Pack> packs = new ArrayList<>();

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
		name = "PATTERN_NEEDLE",
		joinColumns = @JoinColumn(name = "pattern_id", referencedColumnName = "pattern_id", nullable = false),
		inverseJoinColumns = @JoinColumn(name = "needle_id", referencedColumnName = "needle_id", nullable = false)
	)
	@JsonManagedReference
	private final List<Needle> needles = new ArrayList<>();

	@Id
	@Column(name = "pattern_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	private Float gaugeDivisor;

	@Column(name = "gauge_pattern")
	private String gaugePattern;

	@Column(name = "row_gauge")
	private Float rowGauge;

	private int yardage;

	@Column(name = "yardage_max")
	private Integer yardageMax;

	@Column(name = "sizes_available", length = 50)
	private String sizesAvailable;

	@Column(name = "notes_html", length = 500)
	private String notesHtml;

	@Column(name = "yarn_weight_description", length = 25)
	private String yarnWeightDescription;

	@Column(name = "yardage_description", length = 50)
	private String yardageDescription;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false)
	@NotNull
	@JsonManagedReference
	private Category category;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", referencedColumnName = "member_id", nullable = false)
	@NotNull
	private Member member;

	@OneToMany(mappedBy = "pattern", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JsonBackReference
	private final List<PatternThumbnail> patternThumbnails = new ArrayList<>();
	@OneToMany(mappedBy = "pattern", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonBackReference
	private final List<PatternLikes> patternLikes = new ArrayList<>();

	public void setDifficultySum(int difficultySum) {
		this.difficultySum = difficultySum;
	}

	public void setDifficultyCnt(int difficultyCnt) {
		this.difficultyCnt = difficultyCnt;
	}

	public void setDifficultyAvg(Float difficultyAvg) {
		this.difficultyAvg = difficultyAvg;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public void setGauge(Float gauge) {
		this.gauge = gauge;
	}

	public void setGaugeDivisor(Float gaugeDivisor) {
		this.gaugeDivisor = gaugeDivisor;
	}

	public void setGaugePattern(String gaugePattern) {
		this.gaugePattern = gaugePattern;
	}

	public void setRowGauge(Float rowGauge) {
		this.rowGauge = rowGauge;
	}

	public void setYardage(int yardage) {
		this.yardage = yardage;
	}

	public void setYardageMax(int yardageMax) {
		this.yardageMax = yardageMax;
	}

	public void setNotesHtml(String notesHtml) {
		this.notesHtml = notesHtml;
	}

	public void addPatternThumbnail(PatternThumbnail patternThumbnail) {
		this.getPatternThumbnails().add(patternThumbnail);
		patternThumbnail.setPattern(this);
	}

	/*
	 * 해당 패턴의 바늘 정보 삽입
	 * */
	public void addNeedle(Needle needle) {
		this.getNeedles().add(needle);
		needle.getPatterns().add(this);
	}

	public void setPatternId(int patternId){
		this.patternId = patternId;
	}


}
