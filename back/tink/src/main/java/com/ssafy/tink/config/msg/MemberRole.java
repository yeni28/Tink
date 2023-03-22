package com.ssafy.tink.config.msg;

public enum MemberRole {

	MANAGER("ROLE_MANAGER","manager"),
	USER("ROLE_USER","user");

	MemberRole(String role, String name) {
		this.role = role;
		this.name = name;
	}

	private final String role;
	private final String name;

}
