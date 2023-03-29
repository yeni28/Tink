package com.ssafy.tink.dto.dsl;

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
@EqualsAndHashCode(of = "thumbnailId")
public class ThumbnailInfoDsl {

	private int thumbnailId;

	@QueryProjection
	public ThumbnailInfoDsl(Number thumbnailId) {
		this.thumbnailId = thumbnailId;
	}
}
