package com.plusl.ai.codewith.session;

import org.springframework.web.bind.annotation.*;

/**
 * 会话控制器
 *
 * @Author PlusL
 */
@RestController
@RequestMapping("/api/session")
public class SessionController {
    
    private final SessionManager sessionManager;
    
    public SessionController(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }
    
    /**
     * 发送消息到会话
     * 
     * @param sessionId 会话ID
     * @param message 消息内容
     * @return 助手回复
     */
    @PostMapping("/{sessionId}/message")
    public String sendMessage(
            @PathVariable String sessionId,
            @RequestBody String message) {
        Session session = sessionManager.getSession(sessionId);
        return session.onUserMessage(message);
    }
    
    /**
     * 创建新会话
     * 
     * @return 新会话ID
     */
    @PostMapping
    public String createSession() {
        Session session = sessionManager.createSession();
        return session.getId();
    }
}