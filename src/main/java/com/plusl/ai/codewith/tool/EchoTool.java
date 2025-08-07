package com.plusl.ai.codewith.tool;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 回声工具实现
 *
 * @Author PlusL
 */
@Component
public class EchoTool implements Tool {
    
    @Override
    public String getName() {
        return "echo";
    }
    
    @Override
    public String getDescription() {
        return "回声工具，用于测试和调试，将输入参数原样返回";
    }
    
    @Override
    public ToolResult apply(Map<String, Object> params) {
        return ToolResult.success(params);
    }
}