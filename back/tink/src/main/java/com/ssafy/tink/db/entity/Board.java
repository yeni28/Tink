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

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.sun.istack.NotNull;

import lombok.Getter;

@Entity
@DynamicUpdate
@DynamicInsert
@Getter
public class Board extends BaseEntity {

	@Id
	@Column(name = "board_id")
	@NotNull
	private int boardId;

	@NotNull
	@Column(length = 50)
	private String title;

	@NotNull
	@Column(length = 10000)
	private String content;

	@ColumnDefault("0")
	private int liked;

	@ColumnDefault("0")
	private int hit;


	@Column(length = 45, name = "board_category")
	private String boardCategory;


	@OneToMany(mappedBy = "board")
	private List<Comment> comments = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	private Member member;


	@OneToOne(fetch = FetchType.LAZY)
	@JoinTable(
		name = "jarang_img",
		joinColumns = @JoinColumn(name = "board_id", referencedColumnName = "board_id", nullable = false),
		inverseJoinColumns = @JoinColumn(name = "thumbnail_id", referencedColumnName = "thumbnail_id", nullable = false)
	)
	private Thumbnail thumbnail;

	@OneToOne(mappedBy = "board")
	private Material material;

	@ManyToOne
	@JoinColumn(name = "pattern_id", referencedColumnName = "pattern_id")
	private Pattern pattern;

	/*
	 * 댓글 등록 메서드
	 * */
	public void addComment(Comment comment) {
		comment.setBoard(this);
		comments.add(comment);
	}

}
