package com.plusl.ai.codewith.cognition.planner;

import com.plusl.ai.codewith.cognition.client.ChatClient;
import com.plusl.ai.codewith.cognition.memory.Memory;
import com.plusl.ai.codewith.infra.entity.BaseChatRequest;
import com.plusl.ai.codewith.infra.entity.ChatMessage;
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
        try {
            // 构建ReAct提示词
            String prompt = buildReActPrompt(memory);
            
            // 调用大模型
            // BaseChatRequest request = new BaseChatRequest();
            // ChatMessage chatMessage = new ChatMessage();
            // chatMessage.setRole("user");
            // request.setMessages(List.of(chatMessage));
            // chatClient.chat(null)
            
            // 解析LLM响应为Action
            return parseResponse("llmResponse");
            
        } catch (Exception e) {
            return Action.ofAnswer("抱歉，处理过程中发生错误: " + e.getMessage());
        }
    }
    
    /**
     * 构建ReAct提示词
     */
    private String buildReActPrompt(Memory memory) {
        StringBuilder prompt = new StringBuilder();
        
        // 添加可用工具信息
        prompt.append("可用工具:\n");
        toolRegistry.getAllTools().forEach((name, tool) -> {
            prompt.append(String.format("- %s: %s\n", name, tool.getDescription()));
        });
        
        prompt.append("\n对话历史:\n");
        memory.getMessages().forEach(msg -> {
            prompt.append(String.format("%s: %s\n", msg.getRole(), msg.getContent()));
        });
        
        prompt.append("\n请按照ReAct模式思考并决定下一步行动。");
        return prompt.toString();
    }
    
    /**
     * 模拟LLM响应（实际项目中替换为真实LLM调用）
     */
    private String simulateLLMResponse(String prompt, Memory memory) {
        // 简单的规则引擎模拟ReAct思考过程
        String lastUserMessage = getLastUserMessage(memory);
        
        if (lastUserMessage.contains("天气")) {
            return "思考: 用户询问天气信息，我需要使用天气工具来获取天气数据。\n" +
                   "行动: 调用get_weather工具";
        } else if (lastUserMessage.contains("搜索") || lastUserMessage.contains("查找")) {
            return "思考: 用户需要搜索信息，我应该使用搜索工具。\n" +
                   "行动: 调用search工具";
        } else if (lastUserMessage.contains("测试") || lastUserMessage.contains("echo")) {
            return "思考: 用户想要测试功能，我可以使用echo工具来演示。\n" +
                   "行动: 调用echo工具";
        } else {
            return "思考: 这是一个一般性问题，我可以直接回答。\n" +
                   "行动: 直接回答用户的问题";
        }
    }
    
    /**
     * 解析LLM响应为Action对象
     */
    private Action parseResponse(String response) {
        if (response.contains("调用") && response.contains("工具")) {
            // 解析工具调用
            if (response.contains("get_weather")) {
                return Action.ofToolCall("get_weather", "{\"location\": \"北京\"}");
            } else if (response.contains("search")) {
                return Action.ofToolCall("search", "{\"query\": \"Java编程\"}");
            } else if (response.contains("echo")) {
                return Action.ofToolCall("echo", "{\"text\": \"ReAct演示\"}");
            }
        }
        
        // 默认返回思考或答案
        if (response.contains("直接回答")) {
            return Action.ofAnswer("我理解您的问题。基于ReAct模式，我已经分析了您的需求并提供了相应的回答。");
        } else {
            return new Action("think", response);
        }
    }
    
    /**
     * 获取最后一条用户消息
     */
    private String getLastUserMessage(Memory memory) {
        return memory.getMessages().stream()
                .filter(msg -> "user".equals(msg.getRole()))
                .reduce((first, second) -> second)
                .map(Memory.Message::getContent)
                .orElse("");
    }
}