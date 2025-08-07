package com.plusl.ai.codewith.infra.entity;

import lombok.Data;

@Data
public class LlmConfigProperty {
    private String apiKey;
    private String url;
    private String model;
}
