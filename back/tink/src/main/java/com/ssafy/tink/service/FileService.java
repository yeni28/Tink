package com.ssafy.tink.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.tink.config.Util.FileUtil;
import com.ssafy.tink.config.ect.BadRequestException;
import com.ssafy.tink.test.FileSample;

@Service
public class FileService {

	private final String originPath;
	private final String thumbPath;

	public FileService(
		@Value("${app.upload.folder}") String path) {
		String folder = FileUtil.createPath(path);
		this.originPath = folder + File.separator + "origin";
		this.thumbPath = folder + File.separator + "thumb";
	}

	public FileSample singleFileupload(MultipartFile file) throws IOException {
		// 유일한 식별ID 생성
		String fileName = UUID.randomUUID().toString() + ".png";
		// 썸네일 이미지 생성
		BufferedImage thumb = FileUtil.createThumbnail(file);
		// 썸네일 이지미가 생성되지 않으면 예외처리
		if( thumb == null ) {
			throw new BadRequestException("이미지가 생성되지 않았습니다.");
		}
		
		InputStream in = file.getInputStream();
		BufferedImage origin = ImageIO.read(in);
		// 실질적으로 파일을 저장 실행
		FileUtil.saveImg(origin, originPath, fileName);
		FileUtil.saveImg(thumb, thumbPath, fileName);
		// 저장한 경로를 저장한 객체를 반환
		return FileSample.builder()
			.originPath(originPath + File.separator + fileName)
			.thumbPath(thumbPath + File.separator + fileName)
			.build();
	}

	public List<FileSample> multiFileupload(MultipartFile[] files) throws IOException{
		List<FileSample> list = new ArrayList<>();
		for(MultipartFile file : files) {
			FileSample sample = singleFileupload(file);
			list.add(sample);
		}
		return list;
	}

}
