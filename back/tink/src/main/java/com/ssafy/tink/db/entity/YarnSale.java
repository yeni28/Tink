package com.ssafy.tink.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "yarn_sale")
@Getter
@Builder
@ToString
@DynamicInsert
@DynamicUpdate
public class YarnSale {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "yarn_sale_id")
	private int yarnSaleId;

	private String img;

	private String url;

	private int price;

	@NotNull
	@Column(length = 45)
	private String gram;

	private String len;

	@Column
	private String needle;

	@Column
	private String ingredient;

	@Column(length = 45)
	private String origin;

	@Column(length = 45)
	private String company;

}
