package com.plusl.ai.codewith.cognition.client;

import com.plusl.ai.codewith.infra.entity.BaseChatRequest;
import reactor.core.publisher.Flux;

/**
 * LLM客户端接口
 *
 * @Author PlusL
 */
public interface LlmClient {

    /**
     * 与LLM进行对话
     *
     * @param chatRequest 消息历史列表
     * @return LLM的回复
     */
    String chat(BaseChatRequest chatRequest);

    Flux<String> chatStream(BaseChatRequest chatRequest);
}