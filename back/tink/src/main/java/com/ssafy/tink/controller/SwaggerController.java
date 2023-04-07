package com.ssafy.tink.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@Api(value = "swagger 사용용도")
@RequestMapping("/swagger")
public class SwaggerController {

	@GetMapping("/doc")
	public String mvSwagger() {
		return "swagger-ui";
	}

	@GetMapping("/test01")
	@ApiOperation(value = "테스트하기 위한 용도")
	public void test01() {

	}

	@GetMapping("/test02")
	@ApiOperation(value = "테스트하기 위한 용도")
	public void test02() {

	}

	@GetMapping("/test03")
	@ApiOperation(value = "테스트하기 위한 용도")
	public void test03() {

	}

	@GetMapping("/test04")
	@ApiOperation(value = "테스트하기 위한 용도")
	public void test04() {

	}

}
