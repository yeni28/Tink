package com.ssafy.tink.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.sun.istack.NotNull;
import lombok.Getter;

@Entity
@Getter
public class Needle {

	@Id
	@Column(name = "needle_id")
	private int needleId;

	private int us;

	private Float metric;

	@Column(name = "us_steel", length = 10)
	private String usSteel;

	@Column(columnDefinition = "TINYINT", length=2)
	private boolean crochet;

	@Column(columnDefinition = "TINYINT", length=2)
	private boolean knitting;

	@Column(length = 10)
	private String hook;

	@Column(length = 30)
	private String name;

	@Column(name = "pretty_metric", length = 10)
	private String prettyMetric;

}
