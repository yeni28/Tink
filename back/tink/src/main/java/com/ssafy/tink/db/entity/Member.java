package com.ssafy.tink.db.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;

@Entity
@Getter
public class Member extends BaseEntity {

	@Id
	@Column(name = "member_id")
	private int memberId;

	private String email;

	private Date birth;

	private String nickname;

	private String role;

	private boolean status;

	@OneToMany(mappedBy = "member")
	private List<Follow> follows = new ArrayList<>();

	@OneToMany(mappedBy = "member")
	private List<Notification> notifications = new ArrayList<>();

	@OneToMany(mappedBy = "member")
	private List<Pattern> patterns;

	@OneToMany(mappedBy = "member")
	private List<Board> boards = new ArrayList<>();

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "image_id", referencedColumnName = "thumbnail_id")
	private Thumbnail thumbnail;

	/*
	 * 알림 등록
	 * */
	public void addNotification(Notification notification) {
		notification.setMember(this);
		notifications.add(notification);
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setThumbnail(Thumbnail thumbnail) {
		this.thumbnail = thumbnail;
	}
}

