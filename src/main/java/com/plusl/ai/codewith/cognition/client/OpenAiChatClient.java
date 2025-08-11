package com.plusl.ai.codewith.cognition.client;

import com.plusl.ai.codewith.infra.entity.BaseChatRequest;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * OpenAI客户端实现
 *
 * @Author PlusL
 */
@Component
@Profile("prod")
public class OpenAiChatClient implements ChatClient {
    
    @Override
    public String chat(BaseChatRequest chatRequest) {
        // TODO: 实现与OpenAI API的实际调用
        return "Mock OpenAI response";
    }

    @Override
    public Flux<String> chatStream(BaseChatRequest chatRequest) {
        return null;
    }
}