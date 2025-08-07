package com.plusl.ai.codewith.infra.event;

import java.time.LocalDateTime;

/**
 * Agent事件基类
 *
 * @Author PlusL
 */
public class AgentEvent {
    
    private final LocalDateTime timestamp;
    private final String sessionId;
    
    public AgentEvent(String sessionId) {
        this.timestamp = LocalDateTime.now();
        this.sessionId = sessionId;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public String getSessionId() {
        return sessionId;
    }
}