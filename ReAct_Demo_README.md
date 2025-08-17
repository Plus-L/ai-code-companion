# ReAct模式AI编程助手演示

## 概述

这个演示实现了基于ReAct（Reasoning and Acting）模式的AI编程助手。ReAct模式结合了推理（Reasoning）和行动（Acting），让AI能够通过思考-行动的循环来解决复杂问题。

## 核心组件

### 1. AgentController
- **路径**: `src/main/java/com/plusl/ai/codewith/ui/rest/AgentController.java`
- **功能**: 提供ReAct演示的REST API接口
- **主要方法**: `demo(String userPrompt)` - 执行ReAct流程演示

### 2. ReActPlanner
- **路径**: `src/main/java/com/plusl/ai/codewith/cognition/planner/ReActPlanner.java`
- **功能**: 实现ReAct规划逻辑，决定下一步行动
- **核心方法**: `plan(Memory memory)` - 基于记忆生成行动计划

### 3. 工具系统
- **EchoTool**: 回声工具，用于测试和调试
- **WeatherTool**: 天气查询工具
- **SearchTool**: 搜索工具

## 使用方法

### 启动应用
```bash
mvn spring-boot:run
```

### 测试ReAct演示

#### 1. 天气查询示例
```bash
curl "http://localhost:8080/api/agent/demo?userPrompt=今天北京的天气怎么样？"
```

#### 2. 搜索功能示例
```bash
curl "http://localhost:8080/api/agent/demo?userPrompt=帮我搜索Java编程相关资料"
```

#### 3. 测试功能示例
```bash
curl "http://localhost:8080/api/agent/demo?userPrompt=测试一下echo功能"
```

#### 4. 一般问答示例
```bash
curl "http://localhost:8080/api/agent/demo?userPrompt=什么是ReAct模式？"
```

## ReAct执行流程

1. **接收用户输入**: 用户提供问题或任务
2. **初始化记忆**: 创建Memory对象，添加系统提示和用户消息
3. **循环执行ReAct步骤**:
   - **思考阶段**: 分析当前状态，决定下一步行动
   - **行动阶段**: 执行具体的工具调用或提供答案
   - **观察阶段**: 记录执行结果，更新记忆
4. **返回执行记录**: 提供完整的推理和行动过程

## 响应示例

```json
[
  "步骤 1: 思考: 用户询问天气信息，我需要使用天气工具来获取天气数据。",
  "步骤 2: 调用工具 get_weather，参数: {location=北京}，结果: 天气: 晴朗, 温度: 25°C, 湿度: 60%, 位置: 北京",
  "步骤 3: 最终答案: 根据天气工具的查询结果，北京今天天气晴朗，温度25°C，湿度60%。"
]
```

## 扩展功能

### 添加新工具
1. 实现`Tool`接口
2. 添加`@Component`注解
3. 在`ReActPlanner`中添加相应的识别逻辑

### 集成真实LLM
替换`ReActPlanner.simulateLLMResponse()`方法，调用真实的LLM API（如OpenAI、Claude等）

### 增强记忆系统
- 添加持久化存储
- 实现上下文窗口管理
- 支持多轮对话记忆

## 技术特点

- **模块化设计**: 清晰的组件分离，易于扩展
- **工具系统**: 灵活的工具注册和调用机制
- **记忆管理**: 完整的对话历史和执行记录
- **错误处理**: 完善的异常处理和错误恢复
- **可观测性**: 详细的执行日志和步骤记录

## 注意事项

- 当前实现使用模拟的LLM响应，生产环境需要集成真实的LLM
- 最大执行步数限制为5步，防止无限循环
- 工具执行结果会自动记录到记忆中
- 支持并发请求，每个请求独立的记忆空间