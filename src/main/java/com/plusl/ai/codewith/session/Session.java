package com.plusl.ai.codewith.session;

import com.plusl.ai.codewith.cognition.memory.Memory;
import com.plusl.ai.codewith.cognition.planner.Action;
import com.plusl.ai.codewith.cognition.planner.Planner;
import com.plusl.ai.codewith.tool.ToolRegistry;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 会话类
 *
 * @author PlusL
 */
@Component
@Scope("prototype")
@Data
public class Session {
    
    private String id;
    private Memory memory;
    private final Planner planner;
    private final ToolRegistry toolRegistry;
    
    public Session(Planner planner, ToolRegistry toolRegistry) {
        this.id = UUID.randomUUID().toString();
        this.memory = new Memory();
        this.planner = planner;
        this.toolRegistry = toolRegistry;
    }
    
    /**
     * 处理用户消息
     * 
     * @param message 用户消息
     * @return 助手回复
     */
    public String onUserMessage(String message) {
        // 添加用户消息到对话历史
        memory.addUserMessage(message);
        
        // 使用Planner生成行动计划
        Action action = planner.plan(memory);
        
        if (action.isAnswer()) {
            // 如果是直接回答，添加到对话历史并返回
            memory.addAssistantMessage(action.content());
            return action.content();
        } else if (action.isToolCall()) {
            // 如果是工具调用，执行工具并获取结果
            // TODO: 解析工具调用信息
            // TODO: 执行工具
            // TODO: 将结果添加到对话历史
            // TODO: 再次调用planner生成最终回答
            return "Tool call not implemented yet";
        }
        
        return "Unknown action type";
    }

}