package com.ssafy.tink.db.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;

@Entity
@DynamicUpdate
@DynamicInsert
@Getter
public class Board extends BaseEntity {

	@Id
	@Column(name = "board_id")
	private int boardId;

	private String title;

	private String content;

	private int liked;

	private int hit;

	@OneToMany(mappedBy = "board")
	private List<Comment> comments = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;

	private String boardCategory;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinTable(
		name = "jarang_img",
		joinColumns = @JoinColumn(name = "board_id", referencedColumnName = "board_id"),
		inverseJoinColumns = @JoinColumn(name = "thumbnail_id", referencedColumnName = "thumbnail_id")
	)
	private Thumbnail thumbnail;

	@OneToOne(mappedBy = "board")
	private Material material;

	/*
	 * 댓글 등록 메서드
	 * */
	public void addComment(Comment comment) {
		comment.setBoard(this);
		comments.add(comment);
	}

}
