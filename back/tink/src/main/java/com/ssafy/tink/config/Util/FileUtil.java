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
			InputStream in = file.getInputStream();
			BufferedImage origin = ImageIO.read(in);

			BufferedImage thumbImage = Thumbnails.of(origin)
				.size(THUMB_WEIGHT, THUMB_HEIGHT)
				.outputQuality(1.0f)
				.outputFormat("png")
				.asBufferedImage();

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

		File fw = new File(path + File.separator + filename);
		// 디렉토리 생성
		String message = makeDir(path);
		log.debug(message);

		ImageIO.write(img, "png", fw);
	}

	public static String createPath(String path) {
		Date date = new Date();
		SimpleDateFormat simple = new SimpleDateFormat("yyyyMMdd");
		String today = simple.format(date);
		String milisecond = String.valueOf(date.getTime());
		String common = path + File.separator + today + File.separator + milisecond;
		return common;
	}

	private static String makeDir(String folder) {
		File fileDir = new File(folder);
		if( !fileDir.isDirectory() ) {
			return fileDir.mkdirs() ? "directory is make" : "directory don't make";
		}
		return "it is not Directory";
	}
}
