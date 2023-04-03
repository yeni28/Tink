package com.ssafy.tink.db.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@DynamicInsert
@IdClass(FollowId.class)
@Getter
public class Follow implements Serializable {

	@Id
	@Column(name = "to_id")
	private int toId;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "from_id")
	private Member member;

	@Column(name = "created_at")
	private String createdAt;

}
