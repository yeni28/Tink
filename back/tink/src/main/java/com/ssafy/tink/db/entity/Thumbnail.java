package com.ssafy.tink.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Thumbnail {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "thumbnail_id")
	private int thumbnailId;

	@Column(name = "main_img")
	private String mainImg;

	@Column(name = "thumb_img")
	private String thumbImg;

	public void setThumbnailId(int thumbnailId) {
		this.thumbnailId = thumbnailId;
	}
}
