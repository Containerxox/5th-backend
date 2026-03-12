package com.spring.ai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfig {
	
	@Bean
	public ChatClient chatClient(ChatClient.Builder builder) {
		return builder
				.defaultSystem("당신은 친절한 Spring 전문가입니다.")
				.build();
	}
	
}
