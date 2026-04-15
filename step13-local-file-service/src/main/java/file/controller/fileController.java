package file.controller;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class fileController {
	
	@Value("${file.upload.path}")
	private String savePath;
	
	// 파일 업로드(저장)
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
	
	// 파일 다운로드
	@GetMapping("/file-download")
	public ResponseEntity<Resource> downloadFile(
			@RequestParam("fileName") String fileName){
		log.info("FileController: /file-download - {}", fileName);
		
		// 0. orginalFilename 추출
		String orginalFilename = fileName.substring(fileName.indexOf("_") + 1);
		
		// 1. 다운로드할 파일 경로
		Path filePath = Paths.get(savePath, fileName);
		
		// 2. 파일 -> Resource 변환
		Resource resource = null;
		try {
			resource = new InputStreamResource(Files.newInputStream(filePath));
		} catch (IOException e) {
			log.info("파일 읽기 실패: {}", e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		// 3. Http Headers, Content-Type, Content-Disposition
		HttpHeaders headers = new HttpHeaders();
		
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDisposition(
				ContentDisposition.builder("attachment")
					.filename(orginalFilename, StandardCharsets.UTF_8)
					.build());
		
		return new ResponseEntity<>(resource, headers, HttpStatus.OK);
	}
}
