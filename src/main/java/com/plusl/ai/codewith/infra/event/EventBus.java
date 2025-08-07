package com.plusl.ai.codewith.infra.event;

/**
 * 事件总线接口
 *
 * @Author PlusL
 */
public interface EventBus {
    
    /**
     * 发布事件
     * 
     * @param event 事件对象
     */
    void publish(Object event);
    
    /**
     * 订阅事件
     * 
     * @param eventType 事件类型
     * @param handler 事件处理器
     * @param <T> 事件类型
     */
    <T> void subscribe(Class<T> eventType, EventHandler<T> handler);
    
    /**
     * 事件处理器函数式接口
     * 
     * @param <T> 事件类型
     */
    @FunctionalInterface
    interface EventHandler<T> {
        void handle(T event);
    }
}