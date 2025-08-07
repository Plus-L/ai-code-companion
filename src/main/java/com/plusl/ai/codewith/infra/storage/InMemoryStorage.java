package com.plusl.ai.codewith.infra.storage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 基于内存的存储实现
 *
 * @Author PlusL
 */
@Component
public class InMemoryStorage implements StoragePort {
    
    private final Map<String, String> storage = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper;
    
    public InMemoryStorage(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    
    @Override
    public <T> void put(String key, T value) {
        try {
            String json = objectMapper.writeValueAsString(value);
            storage.put(key, json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize object", e);
        }
    }
    
    @Override
    public <T> Optional<T> get(String key, Class<T> clazz) {
        String json = storage.get(key);
        if (json == null) {
            return Optional.empty();
        }
        
        try {
            T value = objectMapper.readValue(json, clazz);
            return Optional.of(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to deserialize object", e);
        }
    }
    
    @Override
    public void remove(String key) {
        storage.remove(key);
    }
}