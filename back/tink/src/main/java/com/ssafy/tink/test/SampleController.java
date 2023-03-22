package com.ssafy.tink.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class SampleController {

	@GetMapping("/oauth2/redirect")
	public String redirect() {

		System.out.println("리다이렉션 통과 중!!");

		return "redirect";
	}
}
