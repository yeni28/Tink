package com.ssafy.tink.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@IdClass(NotificationId.class)
@Setter
@Getter
@DynamicInsert
public class Notification {

	@Id
	@Column(name = "notification_id")
	private int notificationId;

	@Id
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne
	@JoinColumn(name = "board_id", nullable = false)
	@NotNull
	private Board board;

	@Column(name = "read_status", columnDefinition = "TINYINT", length=2)
	@ColumnDefault("0")
	private boolean readStatus;

}
