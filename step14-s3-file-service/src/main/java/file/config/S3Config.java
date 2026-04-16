package file.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Configuration
public class S3Config {

 @Value("${cloud.aws.region.static}")
 private String region;

 /**
 * S3Client Bean — 업로드·다운로드·삭제에 사용
 *
 * DefaultCredentialsProvider를 사용하여 자격증명 자동 탐색
 * - 로컬 : ~/.aws/credentials 또는 환경변수
 * - EC2 : IAM Role (Instance Profile)
 * → 환경별로 코드 변경 불필요
 */
 @Bean
 public S3Client s3Client() {
 return S3Client.builder()
		 		.region(Region.of(region))
		 		.build();
 }

 /**
 * S3Presigner Bean — Presigned URL 생성에 사용
 *
 * S3Client와 별도로 등록 필요
 * Spring Cloud AWS를 사용해도 S3Presigner는 AutoConfiguration에서 제외되므로
 * 반드시 별도 Bean으로 등록해야 한다
 */
 @Bean
 public S3Presigner s3Presigner() {
 return S3Presigner.builder().
		 			region(Region.of(region))
		 			.build();
 }
}