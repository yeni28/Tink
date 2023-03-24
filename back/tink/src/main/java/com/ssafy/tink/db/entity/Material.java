package com.ssafy.tink.db.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;

@Entity
@Getter
public class Material {

	@Id
	@Column(name = "material_id")
	private int materialId;

	@Column(name = "yarn_name")
	private String yarnName;

	@Column(name = "yarn_weight")
	private String yarnWeight;

	@Column(name = "yarn_length")
	private String yarnLength;

	private String needle;

	private String time;

	@OneToOne
	@JoinColumn(name = "board_id", referencedColumnName = "board_id")
	private Board board;

	@OneToMany
	@JoinTable(
		name = "MATERIAL_PATTERN",
		joinColumns = @JoinColumn(name = "material_id", referencedColumnName = "material_id"),
		inverseJoinColumns = @JoinColumn(name = "pattern_id", referencedColumnName = "pattern_id")
	)
	private List<Pattern> patterns = new ArrayList<>();

}
