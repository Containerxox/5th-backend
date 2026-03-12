package com.spring.ai.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AiResponse {
    private String answer;
    private Integer promptTokens;
    private Integer generationTokens;
    private Integer totalTokens;
}