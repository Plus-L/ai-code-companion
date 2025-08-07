package com.plusl.ai.codewith.ui.rest;

import com.plusl.ai.codewith.cognition.client.LlmClient;
import com.plusl.ai.codewith.infra.entity.BaseChatRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/chat")
@Tag(name = "大模型请求Controller")
public class LlmController {

    @Autowired
    private LlmClient llmClient;

    @PostMapping("/completion")
    public String completion(@RequestBody BaseChatRequest messages) {
        return llmClient.chat(messages);
    }

    @PostMapping(value = "/completion/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> completionStream(@RequestBody BaseChatRequest messages) {
        return llmClient.chatStream(messages);
    }
}
