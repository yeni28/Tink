package com.ssafy.tink.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;

@Entity
@Getter
public class Thumbnail {

	@Id
	@Column(name = "thumbnail_id")
	private int thumbnailId;

	@Column(name = "main_img")
	private String mainImg;

	@Column(name = "thumb_img")
	private String thumbImg;

}
