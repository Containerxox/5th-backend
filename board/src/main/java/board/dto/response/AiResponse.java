package board.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AiResponse {

    private String result;       // AI 응답 결과
    private String type;         // category / summary / correct
    private boolean success;     // 성공 여부

    public static AiResponse of(String result, String type) {
        return new AiResponse(result, type, true);
    }

    public static AiResponse error(String type) {
        return new AiResponse(null, type, false);
    }
}