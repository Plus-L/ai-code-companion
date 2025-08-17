package com.plusl.ai.codewith.cognition.memory;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.plusl.ai.codewith.infra.common.CommonConstants.ROLE_ASSISTANT;
import static com.plusl.ai.codewith.infra.common.CommonConstants.ROLE_SYSTEM;
import static com.plusl.ai.codewith.infra.common.CommonConstants.ROLE_USER;

/**
 * 对话类
 *
 * @author PlusL
 */
@Data
public class Memory {
    private String id;
    private List<Message> messages;
    private Integer maxMessages = 100;
    private LocalDateTime createdAt;

    public Memory() {
        this.id = UUID.randomUUID().toString();
        this.messages = new ArrayList<>();
        this.createdAt = LocalDateTime.now();
    }

    public Memory(String id, List<Message> messages, LocalDateTime createdAt) {
        this.id = id;
        this.messages = messages;
        this.createdAt = createdAt;
    }

    /**
     * 添加用户消息
     *
     * @param content 消息内容
     */
    public void addUserMessage(String content) {
        messages.add(new Message(ROLE_USER, content, LocalDateTime.now()));
    }

    /**
     * 添加助手消息
     *
     * @param content 消息内容
     */
    public void addAssistantMessage(String content) {
        messages.add(new Message(ROLE_ASSISTANT, content, LocalDateTime.now()));
    }

    /**
     * 添加 system prompt
     *
     * @param content
     */
    public void addSystemMessage(String content) {
        messages.add(new Message(ROLE_SYSTEM, content, LocalDateTime.now()));
    }

    /**
     * 添加工具调用消息
     *
     * @param toolName 工具名称
     * @param params   参数
     * @param result   结果
     */
    public void addToolCallMessage(String toolName, String params, String result) {
        String content = String.format("Tool: %s, Params: %s, Result: %s", toolName, params, result);
        messages.add(new Message("tool", content, LocalDateTime.now()));
    }

    /**
     * 消息内部类
     */
    @Data
    @AllArgsConstructor
    public static class Message {
        private String role;
        private String content;
        private LocalDateTime timestamp;
    }
}