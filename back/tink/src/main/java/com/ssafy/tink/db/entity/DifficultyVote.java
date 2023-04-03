package com.ssafy.tink.db.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "difficulty_vote")
@IdClass(DifficultyVoteId.class)
@Getter
public class DifficultyVote {

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", referencedColumnName = "member_id")
	private Member member;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pattern_id", referencedColumnName = "pattern_id")
	private Pattern pattern;

	@NotNull
	private int score;

	@Builder
	public DifficultyVote(Member member, Pattern pattern, int score) {
		this.member = member;
		this.pattern = pattern;
		this.score = score;
	}
}
