package file.health;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.HeadBucketRequest;

@RequiredArgsConstructor
@Component("s3")  // /actuator/health 에서 "s3" 키로 표시됨
public class S3HealthIndicator implements HealthIndicator {

    private final S3Client s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    @Override
    public Health health() {
        try {
            // 버킷 존재 여부 확인 (실제 객체 조회 없이 메타데이터만 확인)
            s3Client.headBucket(HeadBucketRequest.builder()
                    .bucket(bucketName)
                    .build());

            return Health.up()
                    .withDetail("bucket", bucketName)
                    .withDetail("status", "connected")
                    .build();
        } catch (Exception e) {
            return Health.down()
                    .withDetail("bucket", bucketName)
                    .withDetail("error", e.getMessage())
                    .build();
        }
    }
}
