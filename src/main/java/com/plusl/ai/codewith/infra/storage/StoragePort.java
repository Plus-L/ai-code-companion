package com.plusl.ai.codewith.infra.storage;

import java.util.Optional;

/**
 * 存储端口接口
 *
 * @Author PlusL
 */
public interface StoragePort {
    
    /**
     * 保存键值对
     * 
     * @param key 键
     * @param value 值
     * @param <T> 值的类型
     */
    <T> void put(String key, T value);
    
    /**
     * 根据键获取值
     * 
     * @param key 键
     * @param clazz 值的类型
     * @param <T> 值的类型
     * @return 值的Optional包装
     */
    <T> Optional<T> get(String key, Class<T> clazz);
    
    /**
     * 根据键删除值
     * 
     * @param key 键
     */
    void remove(String key);
}