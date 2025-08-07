package com.plusl.ai.codewith.cognition.client;

import com.plusl.ai.codewith.infra.entity.BaseChatRequest;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * Mock LLM客户端实现，用于测试
 *
 * @Author PlusL
 */
@Component
@Profile("test")
public class MockLlmClient implements LlmClient {
    
    @Override
    public String chat(BaseChatRequest chatRequest) {
        // 模拟LLM回复
        return "{\"tool\": \"echo\", \"params\": {\"text\": \"Hello, this is a mock response!\"}}";
    }

    @Override
    public Flux<String> chatStream(BaseChatRequest chatRequest) {
        return null;
    }
}