package com.plusl.ai.codewith.tool;

import lombok.Getter;

import java.util.Map;

/**
 * 工具接口
 *
 * @Author PlusL
 */
public interface Tool {
    
    /**
     * 获取工具名称
     * 
     * @return 工具名称
     */
    String getName();
    
    /**
     * 获取工具描述
     * 
     * @return 工具描述
     */
    String getDescription();
    
    /**
     * 应用工具
     * 
     * @param params 参数映射
     * @return 工具执行结果
     */
    ToolResult apply(Map<String, Object> params);
    
    /**
     * 工具执行结果类
     */
    @Getter
    class ToolResult {
        private final String status;
        private final Object data;
        private final String errorMessage;
        
        public ToolResult(String status, Object data, String errorMessage) {
            this.status = status;
            this.data = data;
            this.errorMessage = errorMessage;
        }
        
        public static ToolResult success(Object data) {
            return new ToolResult("success", data, null);
        }
        
        public static ToolResult failure(String errorMessage) {
            return new ToolResult("failure", null, errorMessage);
        }
    }
}