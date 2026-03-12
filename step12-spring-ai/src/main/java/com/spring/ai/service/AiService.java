package com.spring.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.spring.ai.dto.AiResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AiService {

    private final ChatClient chatClient;

    public AiResponse chat(String userMessage) {

        // ① Prompt 생성


        // ② chatResponse()


        // ③ 답변 텍스트 추출


        // ④ 토큰 사용량 추출


        // ⑤ DTO 반환
        
			  return null;
    }
}