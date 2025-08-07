package com.plusl.ai.codewith.cognition.planner;

/**
 * 行动计划类
 *
 * @param type Getters
 * @Author PlusL
 */
public record Action(String type, String content) {

    public static Action ofAnswer(String answer) {
        return new Action("answer", answer);
    }

    public static Action ofToolCall(String toolName, String params) {
        return new Action("tool_call", String.format("{\"tool\": \"%s\", \"params\": %s}", toolName, params));
    }

    public boolean isAnswer() {
        return "answer".equals(type);
    }

    public boolean isToolCall() {
        return "tool_call".equals(type);
    }
}