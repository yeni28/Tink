package com.ssafy.tink.db.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pattern_likes")
@IdClass(PatternLikeId.class)
@Getter
@NoArgsConstructor
public class PatternLike extends BaseEntity {

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", referencedColumnName = "member_id", nullable = false)
	private Member member;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pattern_id", referencedColumnName = "pattern_id", nullable = false)
	@JsonManagedReference
	private Pattern pattern;

	@Builder
	public PatternLike(Member member, Pattern pattern) {
		this.member = member;
		this.pattern = pattern;
	}

}
