package file.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class fileController {
	
	@Value("${file.upload.path}")
	private String savePath;
	
	@PostMapping("/file-upload")
	public ResponseEntity<String> uploadFile(
			@RequestPart("file") MultipartFile file){
		
		log.info("FileController: /file-upload");
		log.info("파일명: " + file.getOriginalFilename());
		log.info("파일크기: {}bytes", file.getSize());
		log.info("MIME 타입: " + file.getContentType());
		log.info("savePath: {}",savePath);
		
		// 1. 저장 디렉터리 (없으면)생성
		File saveDir = new File(savePath);
		if(!saveDir.exists()) {
			saveDir.mkdirs(); // mkdir -p
		}
		
		// 2. 파일 저장
		String uuid = UUID.randomUUID().toString();
		String originalFilename =  file.getOriginalFilename();
		String savedFileName = uuid + "_" + originalFilename; 
		
		Path saveDirPath = Paths.get(savePath,savedFileName);
		try {
			file.transferTo(saveDirPath.toFile());
			log.info("파일 저장 완료: {}",saveDirPath);
		}catch (IllegalStateException | IOException e){
			log.error("파일 저장 실패: {}",e.getMessage());
			return ResponseEntity
								.status(HttpStatus.INTERNAL_SERVER_ERROR)
								.body("파일 저장 실패: " + e.getMessage());
		}
		
		
		
		
		
		return ResponseEntity.ok("업로드");
		 
	}
}
