package com.ssafy.tink.db.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

	@CreatedDate
	@Column(name = "created_date", updatable = false)
	private LocalDateTime createdDate;

	@LastModifiedDate
	@Column(name = "updated_date")
	private LocalDateTime updatedDate;

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * 엔티티를 저장할 때 자동으로 createdDate 값을 설정
	 */
	public void setCreatedDate(LocalDateTime createdDate) {
		if (this.createdDate == null) {
			this.createdDate = createdDate;
		}
	}
}
