package com.ssafy.tink.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.tink.service.FileService;

@RestController
@RequestMapping
public class SampleController {

	@Autowired
	private FileService fileService;

	@GetMapping("/redirect")
	public String redirect() {

		System.out.println("리다이렉션 통과 중!!");

		return "redirect";
	}

	@PostMapping("/file/upload")
	public FileSample fileUpload(@RequestPart(name = "file")MultipartFile file) throws Exception{
		FileSample sample = fileService.upload(file);
		return sample;
	}
}
