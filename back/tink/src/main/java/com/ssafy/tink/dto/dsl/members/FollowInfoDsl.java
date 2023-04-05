package com.ssafy.tink.dto.dsl.members;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.querydsl.core.annotations.QueryProjection;
import com.ssafy.tink.db.entity.Member;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class FollowInfoDsl {

	private Long fromId;
	@Builder.Default
	private Set<MemberInfoDsl> member = new HashSet<>();

	@QueryProjection
	public FollowInfoDsl(Long fromId, Set<MemberInfoDsl> member) {
		this.fromId = fromId;
		this.member = member;
	}
}
