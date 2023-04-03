package com.ssafy.tink.dto;

import java.util.ArrayList;
import java.util.List;

import com.ssafy.tink.db.entity.Follow;
import com.ssafy.tink.db.entity.Thumbnail;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class MemberInfoDto {

	private String email;
	private String nickname;
	private long follows;
	private long follower;
	private boolean isFollow;
	private Thumbnail thumbnail;

	@Builder
	public MemberInfoDto(
		String email,
		String nickname,
		Thumbnail thumbnail,
		long follower,
		long follows,
		boolean isFollow) {
		this.email = email;
		this.nickname = nickname;
		this.thumbnail = thumbnail;
		this.follower = follower;
		this.follows = follows;
		this.isFollow = isFollow;
	}
}
