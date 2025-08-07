package com.plusl.ai.codewith.infra.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

/**
 * 基于Spring的事件总线实现
 *
 * @Author PlusL
 */
@Component
public class SpringEventBus implements EventBus {
    
    private final ApplicationEventPublisher eventPublisher;
    private final Map<Class<?>, Consumer<Object>> handlers = new ConcurrentHashMap<>();
    
    public SpringEventBus(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }
    
    @Override
    public void publish(Object event) {
        eventPublisher.publishEvent(event);
        // 处理本地订阅者
        Consumer<Object> handler = handlers.get(event.getClass());
        if (handler != null) {
            handler.accept(event);
        }
    }
    
    @Override
    public <T> void subscribe(Class<T> eventType, EventHandler<T> handler) {
        handlers.put(eventType, (Consumer<Object>) obj -> handler.handle((T) obj));
    }
}