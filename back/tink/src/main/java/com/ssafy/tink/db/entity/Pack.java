package com.ssafy.tink.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Getter;

@Entity
@DynamicUpdate
@DynamicInsert
@Getter
@Table(name = "pack")
public class Pack {

	@Id
	@Column(name = "pack_id")
	private int packId;

	@Column(name = "primary_pack_id")
	private String primaryPackId;

	@Column(name = "prject_id")
	private int projectId;

	private String skeins;

	@Column(name = "stash_id")
	private int stashId;

	@Column(name = "total_grams")
	private String totalGrams;

	@Column(name = "total_meters")
	private String totalMeters;

	@Column(name = "total_ounces")
	private String totalOunces;

	@Column(name = "total_yards")
	private String totalYards;

	@Column(name = "yarn_id")
	private int yarnId;

	@Column(name = "yarn_name")
	private String yarnName;

	@Column(name = "yarn_weight_crochet_gauge")
	private String yarnWeightCrochetGauge;

	@Column(name = "yarn_weight_id")
	private int yarnWeightId;

	@Column(name = "yarn_weight_knit_gauge")
	private String yarnWeightKnitGauge;

	@Column(name = "yarn_weight_max_gauge")
	private String yarnWeightMaxGauge;

	@Column(name = "yarn_weight_min_gauge")
	private String yarnWeightMinGauge;

	@Column(name = "yarn_weight_name")
	private String yarnWeightName;

	@Column(name = "yarn_weight_ply")
	private String yarnWeightPly;

	@Column(name = "yarn_weight_wpi")
	private String yarnWeightWpi;

	private String colorway;

	@Column(name = "shop_name")
	private String shopName;

	@Column(name = "yarn_yarn_company_name")
	private String yarnCompanyName;

	@Column(name = "yarn_yarn_company_id")
	private int yarnCompanyId;

	@Column(name = "quantity_description")
	private String quantityDescription;

	@Column(name = "personal_name")
	private String personalName;

	@Column(name = "dye_lot")
	private String dyeLot;

	@Column(name = "color_family_id")
	private int colorFamilyId;

	@Column(name = "grams_per_skein")
	private Float gramsPerSkein;

	@Column(name = "yards_per_skein")
	private Float yardsPerSkein;

	@Column(name = "meters_per_skein")
	private Float metersPerSkein;

	@Column(name = "ounces_per_skein")
	private Float ouncesPerSkein;

	@Column(name = "prefer_metric_weight", columnDefinition = "TINYINT", length=1)
	private Boolean preferMetricWeight;

	@Column(name = "prefer_metric_length", columnDefinition = "TINYINT", length=1)
	private Boolean preferMetricLength;

	@Column(name = "shop_id")
	private int shopId;

	@Column(name = "thread_size")
	private String threadSize;

	@ManyToOne
	@JoinColumn(name = "pattern_id", nullable = false)
	private Pattern pattern;
}
