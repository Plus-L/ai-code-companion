package com.plusl.ai.codewith.tool;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 搜索工具实现
 *
 * @Author PlusL
 */
@Component
public class SearchTool implements Tool {
    
    @Override
    public String getName() {
        return "search";
    }
    
    @Override
    public String getDescription() {
        return "搜索相关信息和资料";
    }
    
    @Override
    public ToolResult apply(Map<String, Object> params) {
        String query = (String) params.get("query");
        if (query == null || query.isEmpty()) {
            return ToolResult.failure("搜索查询不能为空");
        }
        
        // 模拟搜索结果
        String searchResult = String.format("搜索 '%s' 的结果: 找到了相关的编程资料和教程，包括基础语法、最佳实践和示例代码。", query);
        return ToolResult.success(searchResult);
    }
}
