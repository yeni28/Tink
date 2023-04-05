package com.ssafy.tink.db.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
public class Needle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "needle_id")
	private int needleId;

	private int us;

	private Float metric;

	@Column(name = "us_steel", length = 10)
	private String usSteel;

	@Column(columnDefinition = "TINYINT", length = 2)
	private Boolean crochet;

	@Column(columnDefinition = "TINYINT", length = 2)
	private Boolean knitting;

	@Column(length = 10)
	private String hook;

	@Column(length = 30)
	private String name;

	@Column(name = "pretty_metric", length = 10)
	private String prettyMetric;

	@ManyToMany
	@JsonBackReference
	private List<Pattern> patterns = new ArrayList<>();

	@Builder
	public Needle(int needleId, int us, Float metric, String usSteel, boolean crochet, boolean knitting,
		String hook, String name, String prettyMetric, List<Pattern> patterns) {
		this.needleId = needleId;
		this.us = us;
		this.metric = metric;
		this.usSteel = usSteel;
		this.crochet = crochet;
		this.knitting = knitting;
		this.hook = hook;
		this.name = name;
		this.prettyMetric = prettyMetric;
		this.patterns = patterns;
	}

}
