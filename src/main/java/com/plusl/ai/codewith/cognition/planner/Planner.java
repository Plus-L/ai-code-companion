package com.plusl.ai.codewith.cognition.planner;

import com.plusl.ai.codewith.cognition.memory.Memory;

/**
 * 规划器接口
 *
 * @author PlusL
 */
public interface Planner {
    
    /**
     * 根据对话历史制定行动计划
     * 
     * @param memory 对话历史
     * @return 行动计划
     */
    Action plan(Memory memory);
}