package com.plusl.ai.codewith.cognition.planner;

import com.plusl.ai.codewith.cognition.client.ChatClient;
import com.plusl.ai.codewith.cognition.memory.Memory;
import com.plusl.ai.codewith.tool.ToolRegistry;
import org.springframework.stereotype.Component;

/**
 * 基于ReAct（Reasoning + Action）的规划器实现
 *
 * @author PlusL
 * @since 0.0.1
 */
@Component
public class ReActPlanner implements Planner {
    
    private final ChatClient chatClient;
    private final ToolRegistry toolRegistry;
    
    public ReActPlanner(ChatClient chatClient, ToolRegistry toolRegistry) {
        this.chatClient = chatClient;
        this.toolRegistry = toolRegistry;
    }
    
    @Override
    public Action plan(Memory memory) {
        // TODO: 实现ReAct规划逻辑
        // 构建Prompt
        // 调用LLM
        // 解析结果为Action对象
        return new Action("echo", "{\"text\": \"Mock ReAct response\"}");
    }
}