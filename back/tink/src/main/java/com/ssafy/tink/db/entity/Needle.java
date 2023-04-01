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
	private boolean crochet;

	@Column(columnDefinition = "TINYINT", length = 2)
	private boolean knitting;

	@Column(length = 10)
	private String hook;

	@Column(length = 30)
	private String name;

	@Column(name = "pretty_metric", length = 10)
	private String prettyMetric;

	@ManyToMany(mappedBy = "needles")
	private List<Pattern> patterns = new ArrayList<>();

	@Builder
	public Needle(int us, Float metric, String usSteel, boolean crochet, boolean knitting, String hook, String name,
		String prettyMetric) {
		this.us = us;
		this.metric = metric;
		this.usSteel = usSteel;
		this.crochet = crochet;
		this.knitting = knitting;
		this.hook = hook;
		this.name = name;
		this.prettyMetric = prettyMetric;
	}

}
