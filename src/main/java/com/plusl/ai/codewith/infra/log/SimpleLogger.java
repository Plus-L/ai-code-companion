package com.plusl.ai.codewith.infra.log;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 简单日志记录器
 *
 * @Author PlusL
 */
@Component
public class SimpleLogger {
    
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    public void info(String message) {
        log("INFO", message);
    }
    
    public void warn(String message) {
        log("WARN", message);
    }
    
    public void error(String message) {
        log("ERROR", message);
    }
    
    public void debug(String message) {
        log("DEBUG", message);
    }
    
    private void log(String level, String message) {
        System.out.println(
            String.format("[%s] %s - %s", 
                LocalDateTime.now().format(FORMATTER), 
                level, 
                message)
        );
    }
}