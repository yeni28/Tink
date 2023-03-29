package com.ssafy.tink.dto.dsl;

import java.util.ArrayList;
import java.util.List;

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
public class MemberInfoDsl {

	private long memberId;
	private String email;

	@Builder.Default
	private List<ThumbnailInfoDsl> thumbnailInfoDsl = new ArrayList<>();

	@QueryProjection
	public MemberInfoDsl(long memberId, String email, List<ThumbnailInfoDsl> thumbnailInfoDsl) {
		this.memberId = memberId;
		this.email = email;
		this.thumbnailInfoDsl = thumbnailInfoDsl;
	}
}
