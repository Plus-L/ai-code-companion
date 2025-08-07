package com.plusl.ai.codewith.session;

import com.plusl.ai.codewith.infra.storage.StoragePort;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 会话管理器
 *
 * @Author PlusL
 */
@Component
public class SessionManager {
    
    private final Map<String, Session> sessions = new ConcurrentHashMap<>();
    private final Session sessionPrototype;
    private final StoragePort storagePort;
    
    public SessionManager(Session sessionPrototype, StoragePort storagePort) {
        this.sessionPrototype = sessionPrototype;
        this.storagePort = storagePort;
    }
    
    /**
     * 获取或创建会话
     * 
     * @param sessionId 会话ID
     * @return 会话实例
     */
    public Session getSession(String sessionId) {
        return sessions.computeIfAbsent(sessionId, id -> {
            // 克隆原型会话
            Session newSession = createFromPrototype();
            newSession.setId(id);
            return newSession;
        });
    }
    
    /**
     * 创建新会话
     * 
     * @return 新会话实例
     */
    public Session createSession() {
        Session session = createFromPrototype();
        sessions.put(session.getId(), session);
        return session;
    }
    
    /**
     * 从原型创建会话
     * 
     * @return 会话实例
     */
    private Session createFromPrototype() {
        // 注意：在实际应用中，应该使用更复杂的克隆机制
        return sessionPrototype;
    }
    
    /**
     * 移除会话
     * 
     * @param sessionId 会话ID
     */
    public void removeSession(String sessionId) {
        sessions.remove(sessionId);
    }
}