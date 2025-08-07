package com.plusl.ai.codewith.cognition.memory;

import com.plusl.ai.codewith.infra.storage.StoragePort;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 记忆仓库类
 *
 * @Author PlusL
 */
@Repository
public class MemoryRepository {
    
    private final StoragePort storagePort;
    
    public MemoryRepository(StoragePort storagePort) {
        this.storagePort = storagePort;
    }
    
    /**
     * 保存对话
     * 
     * @param conversation 对话实例
     */
    public void save(Conversation conversation) {
        storagePort.put(conversation.getId(), conversation);
    }
    
    /**
     * 根据ID获取对话
     * 
     * @param id 对话ID
     * @return 对话实例的Optional包装
     */
    public Optional<Conversation> getById(String id) {
        return storagePort.get(id, Conversation.class);
    }
    
    /**
     * 创建新对话
     * 
     * @return 新对话实例
     */
    public Conversation createNew() {
        return new Conversation();
    }
}