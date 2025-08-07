package com.plusl.ai.codewith.infra.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class BaseChatRequest {
    @Schema(description = "消息列表")
    List<ChatMessage> messages;
}
