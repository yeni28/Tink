package com.ssafy.tink.dto.dsl.members;

import com.querydsl.core.annotations.QueryProjection;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) @Builder
@EqualsAndHashCode(of = "materialId")
public class MaterialInfoDsl {

	private int materialId;
	private String yarnName;
	private String yarnWeight;
	private String yarnLength;
	private String needle;
	private String time;
	@QueryProjection
	public MaterialInfoDsl(
		int materialId,
		String yarnName,
		String yarnWeight,
		String yarnLength,
		String needle,
		String time) {
		this.materialId = materialId;
		this.yarnName = yarnName;
		this.yarnWeight = yarnWeight;
		this.yarnLength = yarnLength;
		this.needle = needle;
		this.time = time;

	}
}
