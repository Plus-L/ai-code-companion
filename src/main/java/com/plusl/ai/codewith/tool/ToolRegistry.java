package com.plusl.ai.codewith.tool;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 工具注册表
 *
 * @Author PlusL
 */
@Component
public class ToolRegistry {
    
    private final Map<String, Tool> tools = new ConcurrentHashMap<>();
    
    public ToolRegistry(List<Tool> toolList) {
        toolList.forEach(tool -> tools.put(tool.getName(), tool));
    }
    
    /**
     * 根据名称查找工具
     * 
     * @param name 工具名称
     * @return 工具实例，如果未找到则返回null
     */
    public Tool find(String name) {
        return tools.get(name);
    }
    
    /**
     * 注册工具
     * 
     * @param tool 工具实例
     */
    public void register(Tool tool) {
        tools.put(tool.getName(), tool);
    }
    
    /**
     * 获取所有已注册的工具
     * 
     * @return 工具映射
     */
    public Map<String, Tool> getAllTools() {
        return tools;
    }
}