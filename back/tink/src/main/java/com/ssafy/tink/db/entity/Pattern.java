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

	private Float guage;

	@Column(name = "guage_divisor")
	private String guageDivisor;

	@Column(name = "guage_pattern")
	private String guagePattern;

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

	public void setPatternId(int patternId) {
		this.patternId = patternId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDifficultySum(int difficultySum) {
		this.difficultySum = difficultySum;
	}

	public void setDifficultyCnt(int difficultyCnt) {
		this.difficultyCnt = difficultyCnt;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	public void setGuage(Float guage) {
		this.guage = guage;
	}

	public void setGuageDivisor(String guageDivisor) {
		this.guageDivisor = guageDivisor;
	}

	public void setGuagePattern(String guagePattern) {
		this.guagePattern = guagePattern;
	}

	public void setYardage(int yardage) {
		this.yardage = yardage;
	}

	public void setYardageMax(int yardageMax) {
		this.yardageMax = yardageMax;
	}

	public void setSizesAvailable(String sizesAvailable) {
		this.sizesAvailable = sizesAvailable;
	}

	public void setNotesHtml(String notesHtml) {
		this.notesHtml = notesHtml;
	}

	public void setYarnWeightDescription(String yarnWeightDescription) {
		this.yarnWeightDescription = yarnWeightDescription;
	}

	public void setYardageDescription(String yardageDescription) {
		this.yardageDescription = yardageDescription;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setMember(Member member) {
		this.member = member;
	}
}
