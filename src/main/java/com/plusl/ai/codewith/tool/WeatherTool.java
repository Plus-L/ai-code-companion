package com.plusl.ai.codewith.tool;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 天气工具实现
 *
 * @Author PlusL
 */
@Component
public class WeatherTool implements Tool {
    
    @Override
    public String getName() {
        return "get_weather";
    }
    
    @Override
    public String getDescription() {
        return "获取指定城市的天气信息";
    }
    
    @Override
    public ToolResult apply(Map<String, Object> params) {
        String city = (String) params.get("city");
        if (city == null || city.isEmpty()) {
            return ToolResult.failure("城市参数不能为空");
        }
        
        // 模拟天气数据
        String weatherInfo = String.format("天气: 晴朗, 温度: 25°C, 城市: %s", city);
        return ToolResult.success(weatherInfo);
    }
}