package com.plusl.ai.codewith.agent;

import com.plusl.ai.codewith.cognition.client.LlmClient;
import com.plusl.ai.codewith.cognition.memory.Memory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * BaseAgent 是一个抽象类，定义了所有Agent实现的基本结构和属性。
 * 它包括名称、描述、系统提示和各种操作参数等基本字段。
 *
 * @author plusL
 * @since 0.0.1
 */
@Data
public abstract class BaseAgent {

    /**
     * Agent的名称。
     * 此字段用于标识和日志记录。
     */
    @Schema(description = "Agent名称")
    private String name;

    /**
     * Agent目的和功能的简要描述。
     * 有助于理解Agent在系统中的作用。
     */
    @Schema(description = "Agent目的和功能的简要描述")
    private String description;

    /**
     * 指导Agent行为和响应的系统提示。
     * 通常这是提供给大语言模型的一组指令或上下文。
     */
    @Schema(description = "指导Agent行为和响应的系统提示")
    private String systemPrompt;

    /**
     * 用于确定Agent工作流中下一步的提示。
     * 可以根据对话或任务进度动态更新。
     */
    @Schema(description = "用于确定Agent工作流中下一步的提示")
    private String nextStepPrompt;

    /**
     * 用于与大语言模型（LLM）通信的客户端。
     * 处理与 AI 模型的实际交互以生成响应。
     */
    @Schema(description = "用于与大语言模型（LLM）通信的客户端")
    private LlmClient llmClient;

    /**
     * 存储对话历史和上下文的记忆组件。
     * 允许Agent保持状态并参考之前的交互。
     */
    @Schema(description = "存储对话历史和上下文的记忆组件")
    private Memory memory;

    /**
     * Agent的当前状态。
     * 表示Agent在其执行过程中的位置（例如，思考、行动、完成）。
     */
    @Schema(description = "Agent的当前状态")
    private String state;

    /**
     * Agent在单个任务中允许执行的最大步数。
     * 防止无限循环并确保任务在合理的时间内完成。
     */
    @Schema(description = "Agent在单个任务中允许执行最大的步数")
    private Integer maxSteps;

    /**
     * Agent执行过程中的当前步数。
     * 随着Agent在工作流中的进展而递增。
     */
    @Schema(description = "Agent执行过程中的当前步数")
    private Integer currentStep;

    /**
     * 检测重复操作或响应的阈值。
     * 如果Agent检测到超过此数量的重复项，可能会采取纠正措施。
     */
    @Schema(description = "检测重复操作或响应的阈值")
    private Integer duplicateThreshold;
}
