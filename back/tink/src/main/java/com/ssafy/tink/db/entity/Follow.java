package com.ssafy.tink.db.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sun.istack.NotNull;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@DynamicUpdate
@DynamicInsert
@IdClass(FollowId.class)
@Getter
@EntityListeners(AuditingEntityListener.class)
public class Follow implements Serializable {

	@Id
	@Column(name = "to_id")
	private int toId;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "from_id")
	private Member member;

	@CreatedDate
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

}
