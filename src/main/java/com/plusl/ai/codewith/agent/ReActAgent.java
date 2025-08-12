package com.plusl.ai.codewith.agent;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * ReActAgent 是一个基于 Reasoning 和 Action 模式的抽象智能体实现。
 * 该类继承自 BaseAgent，提供了智能体决策和执行的基本框架。
 *
 * <p>ReActAgent 的核心思想是将复杂的任务分解为思考（Think）和行动（Act）两个阶段，
 * 通过交替执行这两个阶段来实现任务目标。</p>
 *
 * @author plusL
 * @since 0.0.1
 */
@EqualsAndHashCode(callSuper = true)
@Slf4j
@Data
public abstract class ReActAgent extends BaseAgent {

    /**
     * 思考阶段，用于分析当前状态并决定是否需要执行行动。
     *
     * @return 如果需要执行行动则返回 true，否则返回 false
     */
    public abstract boolean think();

    /**
     * 行动阶段，执行具体的任务操作。
     *
     * @return 行动执行结果的描述信息
     */
    public abstract String act();

    /**
     * 执行单个智能体步骤，按照 ReAct 模式进行决策和执行。
     *
     * <p>该方法首先调用 {@link #think()} 方法进行思考决策，
     * 如果决策结果为需要行动，则调用 {@link #act()} 方法执行具体行动；
     * 否则返回表示无需行动的提示信息。</p>
     *
     * @return 步骤执行结果，可能为行动结果或状态提示信息
     */
    @Override
    public String step() {
        try {
            if (think()) {
                return act();
            } else {
                return "Think over, No action taken.";
            }
        } catch (Exception e) {
            log.error("ReActAgent step error: {}", e.getMessage());
            return "Error: " + e.getMessage();
        }
    }
}
