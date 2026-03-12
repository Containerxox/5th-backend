package board.service;

import java.util.stream.Stream;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AiPostService {

    private final ChatClient chatClient;

    // 1. 카테고리 자동 추천
    public String recommendCategory(String title, String content) {
        return null;
    }

    // 2. 게시글 요약 
    public String summarizePost(String title, String content) {
        return null;
    }

    // 3. 맞춤법 교정
    public String correctSpelling(String content) {
        return null;
    }
}