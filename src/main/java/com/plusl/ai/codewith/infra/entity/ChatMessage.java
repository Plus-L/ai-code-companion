package com.plusl.ai.codewith.infra.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * chat消息体
 * </p>
 *
 * @author PlusL
 * @version 1.0
 * @date 2025/08/07 16:09:05
 */
@Data
public class ChatMessage {
    @Schema(description = "角色")
    private String role;
    @Schema(description = "内容")
    private String content;
}
