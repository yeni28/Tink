package com.ssafy.tink.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;

@Entity
@Getter
public class Needle {

	@Id
	@Column(name = "needle_id")
	private int needleId;

	private int us;

	private Float metric;

	@Column(name = "us_steel")
	private String usSteel;

	private boolean crochet;

	private boolean knitting;

	private String hook;

	private String name;

	@Column(name = "pretty_metric")
	private String prettyMetric;

}
