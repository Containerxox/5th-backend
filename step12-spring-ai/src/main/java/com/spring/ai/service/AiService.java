package com.spring.ai.service;

import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.metadata.Usage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

import com.spring.ai.dto.AiResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AiService {

    private final ChatClient chatClient;

    public AiResponse chat(String userMessage) {

        // ① Prompt 생성
    	List<Message> messages = List.of(
    			new UserMessage(userMessage),
    			new SystemMessage("당신은 10년 이상의 Spring Boot 전문가입니다.")
    	);

    	Prompt prompt = new Prompt(messages);

        // ② chatResponse()
    	ChatResponse response = chatClient
    								.prompt(prompt)
    								.call()
    								.chatResponse();

        // ③ 답변 텍스트 추출
    	String answer = response
							.getResult()
							.getOutput()
							.getText();
    	
        // ④ 토큰 사용량 추출
    	Usage usage = response.getMetadata().getUsage();
    	Integer promptTokens = usage.getPromptTokens();
    	Integer generationTokens = usage.getCompletionTokens();
    	Integer totalTokens = usage.getTotalTokens();

        // ⑤ DTO 반환
    	return AiResponse.builder()
    					.answer(answer)
    					.promptTokens(promptTokens)
    					.generationTokens(generationTokens)
    					.totalTokens(totalTokens)
    					.build();
    }
}