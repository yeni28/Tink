package com.ssafy.tink.config.Util;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.util.ThumbnailatorUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtil {
	private static final int THUMB_WEIGHT = 100;
	private static final int THUMB_HEIGHT = 100;
	private static final long MAX_SIZE = 1024 * 1024 * 100; // 100MB
	public static BufferedImage createThumbnail(MultipartFile file) {

		try {
			// file에 데이터를 입력받는다.
			InputStream in = file.getInputStream();
			// 입력된 바이너리 데이터를 이미지버퍼에 저장
			BufferedImage origin = ImageIO.read(in);
			// 버퍼 데이터를 이용해서 썸네일 이지지 생성 ( corbird 라이브러리 사용 )
			BufferedImage thumbImage = Thumbnails.of(origin)
				.size(THUMB_WEIGHT, THUMB_HEIGHT)
				.outputQuality(1.0f)
				.outputFormat("png")
				.asBufferedImage();
			// 썸네일 반환
			return thumbImage;
		} catch(IOException e) {
			log.debug(e.getMessage());
			return null;
		}

	}

	public static void uploadFileByUrl(String url, String savePath) {
		try {
			URL image = new URL(url);
			BufferedInputStream input = new BufferedInputStream(image.openStream());
			FileOutputStream temp = new FileOutputStream(savePath);
			byte[] buffer = new byte[1024];

			int len = 0;
			long fileSize = 0;

			while( (len = (input.read(buffer, 0, buffer.length))) != -1) {
				temp.write(buffer, 0, len);
				fileSize += len;

				if( fileSize >= MAX_SIZE) {
					break;
				}
			}

			temp.flush();
			temp.close();
			input.close();

			if( fileSize <= MAX_SIZE ) {
				log.debug("파일 저장이 완료되었습니다.");
				log.debug("파일 크기 : " + fileSize + "byte");
				return;
			} else {
				log.debug("파일 크기를 초과하였습니다. [파일 삭제 중..]");
				new File(savePath).delete();
				return;
			}
		} catch( IOException e) {

		}
	}

	public static void saveImg(BufferedImage img, String path, String filename) throws IOException{
		// 저장할 파일 경로 설정
		File fw = new File(path + File.separator + filename);
		// 디렉토리 확인 및 생성
		String message = makeDir(path);
		log.debug(message);
		// 버퍼에 이지미를 지정된 확장자 형식에 맞는 이미지로 생성
		ImageIO.write(img, "png", fw);
	}

	public static String createPath(String path) {
		// 날짜를 기준으로 폴더를 생성
		Date date = new Date();
		SimpleDateFormat simple = new SimpleDateFormat("yyyyMMdd");
		String today = simple.format(date);
		// 밀리초를 사용해서 폴더로 지정
		String milisecond = String.valueOf(date.getTime());
		// 날자와 밀리초를 이용해서 폴더 경로를 추가
		String root = path + File.separator + today + File.separator + milisecond;
		return root;
	}

	private static String makeDir(String folder) {
		// 폴더를 가져온다.
		File fileDir = new File(folder);
		if( !fileDir.isDirectory() ) {
			// mkdirs()는 폴더가 없으면 생성해준다.!!
			return fileDir.mkdirs() ? "directory is make" : "directory don't make";
		}
		return "it is not Directory";
	}
}
