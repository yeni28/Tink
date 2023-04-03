package com.ssafy.tink.db.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

	@Column(name = "created_date")
	@CreatedDate
	@JsonIgnore // 직렬화에서 제외할 필드
	private Timestamp createdDate;

	@Column(name = "updated_date")
	@LastModifiedDate
	@JsonIgnore // 직렬화에서 제외할 필드
	private Timestamp updatedDate;

}
