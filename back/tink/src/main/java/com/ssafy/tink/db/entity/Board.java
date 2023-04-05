package com.ssafy.tink.db.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@DynamicUpdate
@DynamicInsert
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "board_id")
	private int boardId;

	@NonNull
	private String title;

	@NonNull
	@Column(length = 1000)
	private String content;

	@ColumnDefault("0")
	private int liked;

	@ColumnDefault("0")
	private int hit;

	// delete : cascade 영속성 전이 에러 해결
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Comment> comments = new HashSet<>();

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pattern_id")
	private Pattern pattern;

	@NonNull
	private String boardCategory;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(
		name = "jarang_img",
		joinColumns = @JoinColumn(name = "board_id", referencedColumnName = "board_id"),
		inverseJoinColumns = @JoinColumn(name = "thumbnail_id", referencedColumnName = "thumbnail_id")
	)
	private Thumbnail thumbnail;

	@OneToOne(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Material material;

	/*
	 * 댓글 등록 메서드
	 * */
	public void addComment(Comment comment) {
		comment.setBoard(this);
		comments.add(comment);
	}

	// qnaGroup 질문글, 소모임 수정
	public void updateBoard(String title, String content){
		this.title= title;
		this.content = content;
	}

}
