package com.plusl.ai.codewith.infra.config;

import com.plusl.ai.codewith.infra.entity.LlmConfigProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 应用配置属性类
 *
 * @Author PlusL
 */
@Configuration
public class AppProperties {
    @Bean
    @ConfigurationProperties(prefix = "ai.openai-format")
    public LlmConfigProperty defaultLlmConfig() {
        return new LlmConfigProperty();
    }
}