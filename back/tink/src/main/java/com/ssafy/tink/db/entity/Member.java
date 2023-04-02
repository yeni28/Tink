package com.ssafy.tink.db.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import com.ssafy.tink.config.msg.AuthProvider;
import com.ssafy.tink.config.msg.MemberRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
public class Member extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "member_id")
	private Long memberId;

	@Column(length = 100)
	private String email;

	private Date birth;

	@Column(length = 50)
	private String nickname;

	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private MemberRole role;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private AuthProvider authProvider;

	@Column(columnDefinition = "TINYINT", length = 2)
	@ColumnDefault("1")
	private boolean status;

	@OneToMany(mappedBy = "member")
	private List<Follow> follows = new ArrayList<>();

	@OneToMany(mappedBy = "member")
	private List<Notification> notifications = new ArrayList<>();

	@OneToMany(mappedBy = "member")
	private List<Pattern> patterns;

	@OneToMany(mappedBy = "member")
	private List<Board> boards = new ArrayList<>();

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "image_id", referencedColumnName = "thumbnail_id", nullable = false)
	private Thumbnail thumbnail;

	/*
	 * 알림 등록
	 * */
	public void addNotification(Notification notification) {
		notification.setMember(this);
		notifications.add(notification);
	}

	public void setMemberId(Long memberId) {
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

	public void setRole(MemberRole role) {
		this.role = role;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setThumbnail(Thumbnail thumbnail) {
		this.thumbnail = thumbnail;
	}
}

