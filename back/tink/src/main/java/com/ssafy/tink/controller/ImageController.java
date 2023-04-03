package com.ssafy.tink.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.tink.dto.BaseResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/img")
@RequiredArgsConstructor
public class ImageController {

	String UPLOAD_PATH = "C:\\myUpload"; // 업로드 할 위치

	// 이미지 불러오기
	@GetMapping("/{fileId}/{fileType}")
	public ResponseEntity<byte[]> getImageFile(@PathVariable String fileId, @PathVariable String fileType) {

		try {
			FileInputStream fis = new FileInputStream(UPLOAD_PATH + "\\" + fileId + "." + fileType);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			byte buffer[] = new byte[1024];
			int length = 0;

			while((length = fis.read(buffer)) != -1) {
				baos.write(buffer, 0, length);
			}

			return new ResponseEntity<byte[]>(baos.toByteArray(), HttpStatus.OK);

		} catch(IOException e) {
			return new ResponseEntity<byte[]>(new byte[] {}, HttpStatus.CONFLICT);
		}
	}

	// 이미지 업로드
	@PostMapping("/uploadImage")
	public BaseResponse<Object> uploadImage(MultipartFile multipartFiles[]) {
		try {
			MultipartFile file = multipartFiles[0];

			String fileId = (new Date().getTime()) + "" + (new Random().ints(1000, 9999).findAny().getAsInt()); // 현재 날짜와 랜덤 정수값으로 새로운 파일명 만들기
			String originName = file.getOriginalFilename(); // ex) 파일.jpg
			String fileExtension = originName.substring(originName.lastIndexOf(".") + 1); // ex) jpg
			originName = originName.substring(0, originName.lastIndexOf(".")); // ex) 파일
			long fileSize = file.getSize(); // 파일 사이즈

			File fileSave = new File(UPLOAD_PATH, fileId + "." + fileExtension); // ex) fileId.jpg
			if(!fileSave.exists()) { // 폴더가 없을 경우 폴더 만들기
				fileSave.mkdirs();
			}

			file.transferTo(fileSave); // fileSave의 형태로 파일 저장

			// System.out.println("fileId= " + fileId);
			// System.out.println("originName= " + originName);
			// System.out.println("fileExtension= " + fileExtension);
			// System.out.println("fileSize= " + fileSize);

			// return new ResponseEntity<Object>("http://localhost:8081/getImage/" + fileId + "/" + fileExtension, HttpStatus.OK);
			String result = "http://localhost:8081/img/" + fileId + "/" + fileExtension;
			return BaseResponse.builder()
				.result(result)
				.resultCode(HttpStatus.OK.value())
				.resultMsg("정상적으로 이미지가 업로드되었습니다.")
				.build();

		} catch(IOException e) {
			// return new ResponseEntity<Object>(null, HttpStatus.CONFLICT);
			return BaseResponse.builder()
				.result(null)
				.resultCode(HttpStatus.CONFLICT.value())
				.resultMsg("정상적으로 이미지가 업로드되었습니다.")
				.build();
		}
	}
}