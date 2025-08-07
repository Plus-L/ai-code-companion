package com.plusl.ai.codewith.tool;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class WebSearchTool implements Tool {
    @Override
    public String getName() {
        return "WebSearch";
    }

    @Override
    public String getDescription() {
        return "搜索网站";
    }

    @Override
    public ToolResult apply(Map<String, Object> params) {
        return null;
    }
}
