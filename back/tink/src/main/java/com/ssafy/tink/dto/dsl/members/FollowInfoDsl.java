package com.ssafy.tink.dto.dsl.members;

import java.util.ArrayList;
import java.util.List;

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

	private int fromId;
	@Builder.Default
	private List<Member> member = new ArrayList<>();

	@QueryProjection
	public FollowInfoDsl(int fromId, List<Member> member) {
		this.fromId = fromId;
		this.member = member;
	}
}
