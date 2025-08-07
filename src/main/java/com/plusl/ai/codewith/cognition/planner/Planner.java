package com.plusl.ai.codewith.cognition.planner;

import com.plusl.ai.codewith.cognition.memory.Conversation;

/**
 * 规划器接口
 *
 * @Author PlusL
 */
public interface Planner {
    
    /**
     * 根据对话历史制定行动计划
     * 
     * @param conversation 对话历史
     * @return 行动计划
     */
    Action plan(Conversation conversation);
}