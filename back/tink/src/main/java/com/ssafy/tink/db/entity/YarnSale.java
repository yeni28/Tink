package com.ssafy.tink.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;
import lombok.Getter;

@Entity
@Table(name = "yarn_sale")
@Getter
public class YarnSale {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "yarn_sale_id")
	private int yarnSaleId;

	private String img;

	private String url;

	private int price;

	@NotNull
	@Column(length = 45)
	private String gram;

	private String len;

	@Column(length = 100)
	private String needle;

	@Column(length = 45)
	private String ingredient;

	@Column(length = 45)
	private String origin;

	@Column(length = 45)
	private String company;

}
