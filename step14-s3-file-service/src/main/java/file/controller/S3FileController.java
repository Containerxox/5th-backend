package file.controller;

import file.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.NoSuchElementException;

@Slf4j
@RequiredArgsConstructor
@RestController
public class S3FileController {

	 private final S3Service s3Service;
	
	 // 연결 테스트
	 @GetMapping("/api/s3/test")
	 public ResponseEntity<String> s3Test() {
		 return ResponseEntity.ok("S3 연결 테스트 성공");
	 }
	
	 // 파일 업로드
	 @PostMapping("/api/s3/files")
	 public ResponseEntity<Long> uploadS3File(
	 @RequestPart(value = "file") MultipartFile file) {
	
		 log.info("S3FileController : POST /api/s3/files - {}", file.getOriginalFilename());
	 
		 try {
			Long fileNo = s3Service.uploadS3File(file);
			return ResponseEntity.ok(fileNo);
			} catch (IllegalArgumentException e) {
				log.info("업로드 실패 (잘못된 요청): {}", e.getMessage());
				return ResponseEntity.badRequest().build();
			}catch (IOException e) {
				log.info("업로드 실패 (IO 오류): {}", e.getMessage());
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
	 
	 }
	
	 // 파일 다운로드
	 @GetMapping("/api/s3/files/{fileNo}")
		 public ResponseEntity<Resource> downloadS3File(
		 @PathVariable("fileNo") Long fileNo) {
		
			 log.info("S3FileController : GET /api/s3/files/{}", fileNo);
			 
			 try {
				 return s3Service.downloadS3File(fileNo);
			 }catch(Exception e) {
				 log.info("다운로드 실패: {}", e.getMessage());
				 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			 }
	
	 }
	
	 // 파일 삭제
	 @DeleteMapping("/api/s3/files/{fileNo}")
	 public ResponseEntity<String> deleteS3File(
		 @PathVariable("fileNo") Long fileNo) {
			
			 log.info("S3FileController : DELETE /api/s3/files/{}", fileNo);
			
			 try {
				 s3Service.deleteS3File(fileNo); 
				 return ResponseEntity.ok("삭제 성공: fileNo= " + fileNo);
			 } catch(Exception e) {
				 log.info("삭제 실패: {}", e.getMessage());
				 return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			 }
		}
			
	 // 파일 목록 조회
	 @GetMapping("/api/s3/files")
	 public ResponseEntity<Map<Long, String>> listFiles() {
		 return ResponseEntity.ok(s3Service.listFiles());
	 }
	 
	 
	 
	 // 다운로드용 Presigned URL 발급
	 @GetMapping("/api/s3/presign/download/{fileNo}")
	 public ResponseEntity<String> getDownloadPresignedUrl(
			 @PathVariable Long fileNo,
			 @RequestParam(defaultValue = "10") int expireMin) {
		 
		 String url = s3Service.generateDownloadPresignedUrl(fileNo, expireMin);
		 
		 return ResponseEntity.ok(url);
	 }
	 
	 
	// 업로드용 Presigned URL 발급
	 @GetMapping("/api/s3/presign/upload")
	 public ResponseEntity<Map<String, String>> getUploadPresignedUrl(
			 @RequestParam String fileName,
			 @RequestParam(defaultValue = "application/octet-stream") String contentType,
			 @RequestParam(defaultValue = "10") int expireMin) {
		 
		 Map<String, String> result = s3Service.generateUploadPresignedUrl(fileName, contentType, expireMin);
		 
		 return ResponseEntity.ok(result);
	 }
}