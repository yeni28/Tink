package com.ssafy.tink.db.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatternLikeId implements Serializable {

	private Long member;

	private int pattern;

}
