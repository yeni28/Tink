package com.ssafy.tink.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ApiModel("NeedleDto")
@Getter
@ToString
public class NeedleDto {
	private String name;
	private Float metric;
	private String prettyMetric;
	private String us;
	private String usSteel;
	private Boolean knitting;
	private Boolean crochet;




}
