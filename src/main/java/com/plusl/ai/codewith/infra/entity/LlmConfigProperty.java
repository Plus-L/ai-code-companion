package com.plusl.ai.codewith.infra.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * LlmConfigProperty 类用于存储和管理大语言模型（LLM）的配置属性。
 *
 * <p>该类包含了与大语言模型服务相关的关键配置信息，如API密钥、服务地址和模型名称。
 * 通过使用Swagger注解，该类的字段在API文档中具有清晰的描述，便于开发者理解和使用。</p>
 *
 * @author Plusl
 * @since 0.0.1
 */
@Data
public class LlmConfigProperty {
    @Schema(description = "模型服务密钥")
    private String apiKey;
    @Schema(description = "模型服务地址")
    private String url;
    @Schema(description = "模型名称")
    private String model;
}
