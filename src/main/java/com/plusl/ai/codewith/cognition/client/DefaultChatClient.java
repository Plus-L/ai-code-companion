package com.plusl.ai.codewith.cognition.client;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.plusl.ai.codewith.infra.entity.BaseChatRequest;
import com.plusl.ai.codewith.infra.entity.ChatMessage;
import com.plusl.ai.codewith.infra.entity.LlmConfigProperty;
import com.plusl.ai.codewith.infra.exception.BaseException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.List;

import static com.plusl.ai.codewith.infra.common.CommonConstants.CONTENT;
import static com.plusl.ai.codewith.infra.common.CommonConstants.ROLE_USER;

@Service
@Slf4j
@RequiredArgsConstructor
public class DefaultChatClient implements ChatClient {

    @Autowired
    private LlmConfigProperty defaultLlmConfig;
    @Autowired
    private OkHttpClient defaultOkHttpClient;

    @Override
    public String chat(BaseChatRequest chatRequest) {
        // 构建请求体
        JSONObject requestBody = genReqBody(chatRequest.getMessages());

        RequestBody body = RequestBody.create(
                requestBody.toString(),
                MediaType.parse(org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
        );

        Request request = new Request.Builder()
                .url(defaultLlmConfig.getUrl())
                .post(body)
                .addHeader("Authorization", "Bearer " + defaultLlmConfig.getApiKey())
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = defaultOkHttpClient.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                JSONObject responseBody = JSONObject.parseObject(response.body().string());
                return responseBody.toString();
            }
        } catch (IOException e) {
            throw new BaseException("call LLM error", e);
        }

        return null;
    }

    @Override
    public Flux<String> chatStream(BaseChatRequest chatRequest) {
        // 构建请求体
        JSONObject requestBody = genReqBody(chatRequest.getMessages());
        requestBody.put("stream", true);

        WebClient client = WebClient.builder()
                .baseUrl(defaultLlmConfig.getUrl())
                .defaultHeader("Authorization", "Bearer " + defaultLlmConfig.getApiKey())
                .defaultHeader("Content-Type", "application/json")
                .build();

        return client.post()
                .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                .accept(org.springframework.http.MediaType.TEXT_EVENT_STREAM)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToFlux(String.class);
    }

    /**
     * 生成请求体
     *
     * @param messages 消息列表
     * @return 请求体
     */
    @NotNull
    private JSONObject genReqBody(List<ChatMessage> messages) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("model", defaultLlmConfig.getModel());

        JSONArray messageArray = new JSONArray();
        for (ChatMessage message : messages) {
            JSONObject msg = new JSONObject();
            msg.put(ROLE_USER, message.getRole());
            msg.put(CONTENT, message.getContent());
            messageArray.add(msg);
        }
        requestBody.put("messages", messageArray);
        return requestBody;
    }
}
