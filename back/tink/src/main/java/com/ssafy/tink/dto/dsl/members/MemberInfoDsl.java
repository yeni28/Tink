package com.ssafy.tink.dto.dsl.members;

import java.util.ArrayList;
import java.util.List;

import com.querydsl.core.annotations.QueryProjection;
import com.ssafy.tink.db.entity.Thumbnail;
import com.ssafy.tink.dto.dsl.members.ThumbnailInfoDsl;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class MemberInfoDsl {

	private Long memberId;
	private String email;
	private String nickname;
	@Builder.Default
	private long follows = 0;
	@Builder.Default
	private long follower = 0;
	@Builder.Default
	private boolean isFollow = false;
	@Builder.Default
	private ThumbnailInfoDsl thumbnailInfoDsl = new ThumbnailInfoDsl();

	@QueryProjection
	public MemberInfoDsl(
		Long memberId,
		String email,
		String nickname,
		ThumbnailInfoDsl thumbnailInfoDsl) {
		this.memberId = memberId;
		this.email = email;
		this.nickname = nickname;
		this.thumbnailInfoDsl = thumbnailInfoDsl;
	}

	public void setFollow(boolean follow) {
		isFollow = follow;
	}

	public void setFollows(long follows) {
		this.follows = follows;
	}

	public void setFollower(long follower) {
		this.follower = follower;
	}
}
