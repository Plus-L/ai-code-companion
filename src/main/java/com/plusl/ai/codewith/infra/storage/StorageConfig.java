package com.plusl.ai.codewith.infra.storage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 存储配置类
 *
 * @Author PlusL
 */
@Configuration
public class StorageConfig {
    
    @Bean
    public StoragePort storagePort(InMemoryStorage inMemoryStorage) {
        return inMemoryStorage;
    }
}