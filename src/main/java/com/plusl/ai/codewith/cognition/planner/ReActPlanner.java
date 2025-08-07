package com.plusl.ai.codewith.cognition.planner;

import com.plusl.ai.codewith.cognition.client.LlmClient;
import com.plusl.ai.codewith.cognition.memory.Conversation;
import com.plusl.ai.codewith.tool.ToolRegistry;
import org.springframework.stereotype.Component;

/**
 * 基于ReAct（Reasoning + Action）的规划器实现
 *
 * @Author PlusL
 */
@Component
public class ReActPlanner implements Planner {
    
    private final LlmClient llmClient;
    private final ToolRegistry toolRegistry;
    
    public ReActPlanner(LlmClient llmClient, ToolRegistry toolRegistry) {
        this.llmClient = llmClient;
        this.toolRegistry = toolRegistry;
    }
    
    @Override
    public Action plan(Conversation conversation) {
        // TODO: 实现ReAct规划逻辑
        // 构建Prompt
        // 调用LLM
        // 解析结果为Action对象
        return new Action("echo", "{\"text\": \"Mock ReAct response\"}");
    }
}