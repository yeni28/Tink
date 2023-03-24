package com.ssafy.tink.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "yarn_sale")
@Getter
public class YarnSale {

	@Id
	@Column(name = "yarn_sale_id")
	private int yarnSaleId;

	private String img;

	private String url;

	private int price;

	private String gram;

	private String len;

	private String needle;

	private String ingredient;

	private String origin;

	private String company;

}
