package file.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

public class FileValidator {

    // 허용 확장자 화이트리스트
    // 블랙리스트 방식(exe 차단)보다 화이트리스트 방식이 안전
    private static final Set<String> ALLOWED_EXTENSIONS = Set.of(
            "jpg", "jpeg", "png", "gif", "webp",   // 이미지
            "pdf", "txt", "md",                    // 문서
            "xlsx", "xls", "csv",                  // 스프레드시트
            "docx", "doc", "pptx", "ppt",         // Office
            "zip", "tar", "gz"                     // 압축
    );

    // 허용 MIME 타입 화이트리스트
    private static final Set<String> ALLOWED_MIME_TYPES = Set.of(
            "image/jpeg", "image/png", "image/gif", "image/webp",
            "application/pdf",
            "text/plain", "text/csv",
            "application/vnd.ms-excel",
            "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet",
            "application/msword",
            "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
            "application/zip"
    );

    // 파일 최대 크기: 10MB
    private static final long MAX_FILE_SIZE = 10L * 1024 * 1024;

    /**
     * 파일 전체 검증 (크기 + 확장자 + MIME 타입)
     *
     * @throws IllegalArgumentException 검증 실패 시
     */
    public static void validate(MultipartFile file) throws IOException {
        validateNotEmpty(file);
        validateFileSize(file);
        validateExtension(file);
        validateMimeType(file);
    }

    // 빈 파일 검증
    private static void validateNotEmpty(MultipartFile file) {
    	if(file == null || file.isEmpty()) {
    		throw new IllegalArgumentException("파일이 비어있습니다.");
    	}
    }

    // 파일 크기 검증
    private static void validateFileSize(MultipartFile file) {
    	if(file.getSize() > MAX_FILE_SIZE) {
    		throw new IllegalArgumentException("파일 크기 초과: " + (file.getSize() / 1024 / 1024) + "MB (최대 100MB)");
    	}
    }

    // 확장자 검증 (화이트리스트)
    private static void validateExtension(MultipartFile file) {
        String originalName = file.getOriginalFilename();

        // 마지막 '.' 이후 소문자 변환
        String ext = originalName.substring(originalName.lastIndexOf('.') + 1)
                .toLowerCase();

        if (!ALLOWED_EXTENSIONS.contains(ext)) {
            throw new IllegalArgumentException("허용되지 않는 확장자: ." + ext);
        }
    }

    // MIME 타입 검증
    // Content-Type 헤더는 클라이언트가 조작 가능 => 실제 바이트(Magic Bytes)로 재확인
    private static void validateMimeType(MultipartFile file) throws IOException {
        String contentType = file.getContentType();

        // Content-Type 헤더 검증
        if(contentType == null || contentType.isBlank()) {
        	throw new IllegalArgumentException("Content-Type을 확인할 수 없습니다.");
        }

        // Magic Bytes 검증 (파일 첫 바이트로 실제 형식 확인)
        try (InputStream is = file.getInputStream()) {
            byte[] header = new byte[8];
            int read = is.read(header);

            if (read < 4) {
                throw new IllegalArgumentException("파일 내용을 읽을 수 없습니다.");
            }

            if (isExecutable(header)) {
                throw new IllegalArgumentException("실행 파일은 업로드할 수 없습니다.");
            }
        }
    }

    // 실행 파일 Magic Bytes 감지
    private static boolean isExecutable(byte[] header) {
        // Windows PE 실행 파일: MZ (0x4D 0x5A)
        if (header[0] == 0x4D && header[1] == 0x5A) return true;

        // ELF (Linux 실행 파일): 0x7F 'E' 'L' 'F'
        if (header[0] == 0x7F && header[1] == 0x45 &&
            header[2] == 0x4C && header[3] == 0x46) return true;

        // Shell script: '#!'
        if (header[0] == 0x23 && header[1] == 0x21) return true;

        return false;
    }
}