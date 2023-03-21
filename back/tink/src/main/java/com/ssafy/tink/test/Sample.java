package com.ssafy.tink.test;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Sample {

	private Long id;
	private String email;
	private String name;
}
