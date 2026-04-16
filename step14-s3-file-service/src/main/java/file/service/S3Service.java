package file.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@RequiredArgsConstructor
@Service
public class S3Service {

 private final S3Client s3Client;

 @Value("${cloud.aws.s3.bucket}")
 private String bucketName;

 // S3 버킷 내 저장 폴더 (prefix)
 private static final String DIR_NAME = "s3_data";

 // DB 대신 메모리 Map으로 파일 메타 임시 관리
 // 실제 운영에서는 DB(RDS) 사용
 private final Map<Long, String> fileStore = new ConcurrentHashMap<>();
 private final AtomicLong fileNoCounter = new AtomicLong(1);

 
 // 파일 업로드
 
 public Long uploadS3File(MultipartFile file) throws IOException {
	 log.info("S3Service.uploadS3File : {}", file.getOriginalFilename());
	
	 if(file == null || file.isEmpty()) {
		 throw new IllegalArgumentException("파일이 비어 있습니다.");
	 }
	 
	 // 1. UUID + 원본 파일명 조합
	 String originalFileName = file.getOriginalFilename();
	 String savedFileName = UUID.randomUUID() + "_" + originalFileName;
	
	 // 2. S3 키 생성 : "s3_data/uuid_파일명"
	 String s3Key = DIR_NAME + "/" + savedFileName;
	
	 // 3. S3 업로드 (PutObjectRequest)
	 // RequestBody.fromInputStream : InputStream + 파일크기 필수
	 PutObjectRequest putRequest = PutObjectRequest.builder()
			 										.bucket(bucketName)
			 										.key(s3Key)
			 										.contentType(file.getContentType())
			 										.contentLength(file.getSize())
			 										.build();
	 RequestBody requestBody = RequestBody.fromInputStream(file.getInputStream(), file.getSize());
	
	 s3Client.putObject(putRequest, requestBody);
	 
	 log.info("S3 업로드 완료 : bucket={}, key={}", bucketName, s3Key);
	
	 
	 // 4. 메모리 Map에 파일 정보 저장
	 Long fileNo = fileNoCounter.getAndIncrement();
	 fileStore.putIfAbsent(fileNo, savedFileName);
	
	 return fileNo; // 다운로드/삭제에 사용할 파일 번호 반환
 }

 //
 // 파일 다운로드
 //
// public ResponseEntity<Resource> downloadS3File(Long fileNo) {
// log.info("S3Service.downloadS3File : fileNo={}", fileNo);
//
// // 1. 메모리 Map에서 파일명 조회
// String savedFileName = fileStore.get(fileNo);
//
// // 원본 파일명 추출 (UUID_ 제거)
//
//
// // 2. S3에서 파일 가져오기 (GetObjectRequest)
//
//
// // ResponseInputStream : S3 응답 스트림 (try-with-resources로 자동 닫힘 주의)
//
//
// // 3. InputStreamResource로 래핑
// Resource resource = new InputStreamResource(s3Stream);
//
// // 4. HTTP 헤더 설정
// HttpHeaders headers = new HttpHeaders();
//
//
// return new ResponseEntity<>(resource, headers, HttpStatus.OK);
// }

 //
 // 파일 삭제
 //
// public void deleteS3File(Long fileNo) {
// log.info("S3Service.deleteS3File : fileNo={}", fileNo);
//
// // 1. 메모리 Map에서 파일명 조회
//
//
// // 2. S3 파일 삭제 (DeleteObjectRequest)
//
//
// // 3. 메모리 Map에서도 제거
// fileStore.remove(fileNo);
//
// log.info("S3 삭제 완료 : key={}", DIR_NAME + "/" + savedFileName);
// }
//
// //
// // 파일 목록 조회 (메모리 Map)
// //
// public Map<Long, String> listFiles() {
// return fileStore;
// }
}
