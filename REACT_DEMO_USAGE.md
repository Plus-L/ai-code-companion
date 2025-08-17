# ReAct演示使用指南

## 快速开始

### 1. 启动应用
```bash
mvn spring-boot:run
```

### 2. 测试不同场景

#### 天气查询
```bash
curl "http://localhost:8080/api/agent/demo?userPrompt=今天北京的天气怎么样？"
```

预期响应：
```json
[
  "步骤 1: 思考: 用户询问天气信息，我需要使用天气工具来获取天气数据。\n行动: 调用get_weather工具",
  "步骤 2: 调用工具 get_weather，参数: {location=北京}，结果: 天气: 晴朗, 温度: 25°C, 湿度: 60%, 位置: 北京"
]
```

#### 搜索功能
```bash
curl "http://localhost:8080/api/agent/demo?userPrompt=帮我搜索Java编程资料"
```

#### Echo测试
```bash
curl "http://localhost:8080/api/agent/demo?userPrompt=测试echo功能"
```

#### 一般问答
```bash
curl "http://localhost:8080/api/agent/demo?userPrompt=解释一下什么是面向对象编程"
```

## ReAct流程说明

1. **用户输入** → 系统接收问题
2. **思考阶段** → AI分析问题，决定策略
3. **行动阶段** → 执行工具调用或直接回答
4. **记录结果** → 更新记忆，准备下一步
5. **循环执行** → 直到获得最终答案

## 系统架构

```
用户请求 → AgentController → ReActPlanner → ToolRegistry → 具体工具
    ↓              ↓              ↓              ↓
  响应结果 ←    Memory    ←    Action    ←    ToolResult
```

## 可扩展性

- **添加新工具**: 实现Tool接口并添加@Component注解
- **集成真实LLM**: 替换ReActPlanner中的模拟响应
- **持久化记忆**: 集成数据库存储对话历史
- **流式响应**: 支持实时的思考过程展示