package com.ssafy.tink.db.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Material {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Column(name = "material_id")
	private int materialId;

	@Column(name = "yarn_name")
	private String yarnName;

	@Column(name = "yarn_weight")
	private Float yarnWeight;

	@Column(name = "yarn_length")
	private Float yarnLength;

	private String needle;

	private String time;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "board_id", referencedColumnName = "board_id", nullable = false)
	private Board board;

	@OneToMany
	@JoinTable(
		name = "MATERIAL_PATTERN",
		joinColumns = @JoinColumn(name = "material_id", referencedColumnName = "material_id"),
		inverseJoinColumns = @JoinColumn(name = "pattern_id", referencedColumnName = "pattern_id")
	)
	private List<Pattern> patterns = new ArrayList<>();

}
