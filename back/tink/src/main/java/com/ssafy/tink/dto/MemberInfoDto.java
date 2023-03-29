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

	private long memberId;
	private String email;
	private long follows;
	private long follower;
	private Thumbnail thumbnail;

	@Builder
	public MemberInfoDto(String email, Thumbnail thumbnail, long follower, long follows) {
		this.email = email;
		this.thumbnail = thumbnail;
		this.follower = follower;
		this.follows = follows;
	}
}
