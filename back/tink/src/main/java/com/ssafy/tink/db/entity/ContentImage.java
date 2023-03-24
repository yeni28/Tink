package com.ssafy.tink.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "content_image")
@Getter
public class ContentImage {

	@Id
	@Column(name = "content_image_id")
	private int contentImageId;

	@Column(name = "image_path")
	private String imagePath;
}
