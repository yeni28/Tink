package com.ssafy.tink.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ApiModel("NeedleDto")
@Getter
@ToString
public class NeedleDto {
	private String name;
	private Float metric;
	private String prettyMetric;
	private int us;
	private String usSteel;
	private Boolean knitting;
	private Boolean crochet;

	@Builder
	public NeedleDto(String name, Float metric, String prettyMetric, int us, String usSteel, Boolean knitting,
		Boolean crochet) {
		this.name = name;
		this.metric = metric;
		this.prettyMetric = prettyMetric;
		this.us = us;
		this.usSteel = usSteel;
		this.knitting = knitting;
		this.crochet = crochet;
	}

	@Builder
	public NeedleDto(String name, Float metric, String prettyMetric, int us, String usSteel) {
		this.name = name;
		this.metric = metric;
		this.prettyMetric = prettyMetric;
		this.us = us;
		this.usSteel = usSteel;
	}
}
