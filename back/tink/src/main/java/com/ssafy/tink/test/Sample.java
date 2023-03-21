package com.ssafy.tink.test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ssafy.tink.config.msg.AuthProvider;
import com.ssafy.tink.config.msg.MemberRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sample {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String email;
	@Column
	private String name;

	@Enumerated(EnumType.STRING)
	private MemberRole	role;
	@Enumerated(EnumType.STRING)
	private AuthProvider authProvider;
}
