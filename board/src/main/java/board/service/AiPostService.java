package board.service;

import java.util.stream.Stream;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AiPostService {

    private final ChatClient chatClient;

    private static final String CATEGORY_PROMPT = """
													title과 content 값을 읽고 가장 적절한 카테고리를 하나만 추천해줘.
													반드시 FRONT, WEB, BACKEND 중 하나만 대문자의 단어로 응답해줘.
													
													제목 : {title}
													내용 : {content}
												""";
    
    // 1. 카테고리 자동 추천
    public String recommendCategory(String title, String content) {
    	
    	
        return chatClient.prompt()
        					.user(u -> u.text(CATEGORY_PROMPT)
        							.param("title", title)
        							.param("content", content)
        						)
        						.call()
        						.content()
        						.trim();
        						
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