package com.plusl.ai.codewith.ui.rest;

import com.plusl.ai.codewith.cognition.memory.Memory;
import com.plusl.ai.codewith.cognition.planner.Action;
import com.plusl.ai.codewith.cognition.planner.ReActPlanner;
import com.plusl.ai.codewith.tool.Tool;
import com.plusl.ai.codewith.tool.ToolRegistry;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/agent")
public class AgentController {
    
    @Autowired
    private ReActPlanner reActPlanner;
    
    @Autowired
    private ToolRegistry toolRegistry;
    
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * ReAct模式演示
     * @param userPrompt 用户输入的问题或任务
     * @return ReAct执行步骤的详细记录
     */
    @RequestMapping("/demo")
    public List<String> demo(@RequestParam String userPrompt) {
        log.info("开始ReAct演示，用户输入: {}", userPrompt);
        
        // 初始化记忆和系统提示
        String defaultSysPrompt = getDefaultSysPrompt();
        Memory memory = new Memory();
        memory.addSystemMessage(defaultSysPrompt);
        memory.addUserMessage(userPrompt);
        
        List<String> executionSteps = new ArrayList<>();
        int maxSteps = 5; // 最大执行步数，防止无限循环
        
        try {
            for (int step = 1; step <= maxSteps; step++) {
                log.info("执行第 {} 步", step);
                
                // 使用ReAct规划器生成下一步行动
                Action action = reActPlanner.plan(memory);
                
                String stepResult = String.format("步骤 %d: %s", step, processAction(action, memory));
                executionSteps.add(stepResult);
                
                // 如果是最终答案，结束循环
                if (action.isAnswer()) {
                    log.info("获得最终答案，结束执行");
                    break;
                }
                
                // 如果达到最大步数，添加总结
                if (step == maxSteps) {
                    executionSteps.add("达到最大执行步数，任务可能需要进一步处理");
                }
            }
            
        } catch (Exception e) {
            log.error("ReAct执行过程中发生错误", e);
            executionSteps.add("执行错误: " + e.getMessage());
        }
        
        return executionSteps;
    }
    
    /**
     * 处理ReAct行动
     */
    private String processAction(Action action, Memory memory) {
        if (action.isAnswer()) {
            // 直接回答
            String answer = action.content();
            memory.addAssistantMessage(answer);
            return "最终答案: " + answer;
            
        } else if (action.isToolCall()) {
            // 工具调用
            return executeToolCall(action, memory);
            
        } else {
            // 思考步骤
            memory.addAssistantMessage("思考: " + action.content());
            return "思考: " + action.content();
        }
    }
    
    /**
     * 执行工具调用
     */
    private String executeToolCall(Action action, Memory memory) {
        try {
            // 解析工具调用内容
            Map<String, Object> toolCallData = objectMapper.readValue(action.content(), Map.class);
            String toolName = (String) toolCallData.get("tool");
            Map<String, Object> params = (Map<String, Object>) toolCallData.get("params");
            
            // 查找并执行工具
            Tool tool = toolRegistry.find(toolName);
            if (tool == null) {
                String error = "未找到工具: " + toolName;
                memory.addAssistantMessage(error);
                return "工具调用失败: " + error;
            }
            
            // 执行工具
            Tool.ToolResult result = tool.apply(params);
            String resultStr = String.format("调用工具 %s，参数: %s，结果: %s", 
                toolName, params, result.getData());
            
            // 记录工具调用结果
            memory.addToolCallMessage(toolName, params.toString(), result.getData().toString());
            
            return resultStr;
            
        } catch (Exception e) {
            String error = "工具调用解析失败: " + e.getMessage();
            memory.addAssistantMessage(error);
            return error;
        }
    }

    private String getDefaultSysPrompt() {
        String sysPromptTemplate = """
            # 角色
            你是一个专业的智能编程助手，作为资深全栈程序员，拥有丰富的架构与开发经验，极为擅长调用各类工具解决实际编程问题。
            
            ## 目标
            - 为用户高效解决编程过程中遇到的问题。
            - 提供专业、准确且易懂的编程建议与指导。
            
            ## 技能
            ### 技能 1: 分析编程问题
            1. 当用户提出编程问题时，仔细询问问题的具体场景、涉及的编程语言、已尝试的解决方法等信息。
            2. 根据用户提供的信息，全面分析问题产生的可能原因。
            
            ### 技能 2: 提供解决方案
            1. 依据对问题的分析，运用自身知识和经验，给出针对性的解决方案。
            2. 如果涉及特定工具或库的使用，详细说明其安装、配置及使用方法。
            3. 若有多种解决方案，对比各方案的优缺点供用户参考。
            ===回复示例===
            针对您的问题，以下是解决方案：
            - **方法一**：
                - **步骤**：[具体步骤1]
                - **优点**：[优点1]
                - **缺点**：[缺点1]
            - **方法二**：
                - **步骤**：[具体步骤2]
                - **优点**：[优点2]
                - **缺点**：[缺点2]
            ===示例结束===
            
            ### 技能 3: 代码示例编写
            1. 根据问题场景和需求，用用户指定的编程语言编写示例代码。
            2. 对示例代码添加详细注释，解释每一行代码的功能和作用。
            
            ## 工具
            你可以使用以下工具或指令，它们又称为动作或actions:
            {tools}
            
            ## 当前的任务执行记录
            {memory}
            
            ## 输出格式
            任务：你收到的需要执行的任务
            思考: 观察你的任务和执行记录，并思考你下一步应该采取的行动
            然后，根据以下格式说明，输出你选择执行的动作/工具:
            {format_instructions}
            
            ## 限制
            - 仅回答与编程相关的问题，拒绝回答无关话题。
            - 提供的解决方案和代码示例需基于常见的编程实践和可靠知识。
            - 输出内容需逻辑清晰、有条理，符合给定格式要求。
            """;
            return  sysPromptTemplate;
    }
    private String getDefaultNextPrompt() {
        String nextPrompt = """
                ## 继续执行任务
                
                基于上述执行记录和当前状态，请继续执行你的任务：
                
                ### 当前状态分析
                - 回顾已完成的步骤和获得的结果
                - 识别当前任务的进展情况
                - 确定是否需要调整策略或方法
                
                ### 下一步行动
                请按照以下格式继续：
                
                **任务**：[重述当前需要完成的任务]
                
                **思考**：
                - 分析当前的执行记录和结果
                - 评估已采取行动的效果
                - 确定下一步最合适的行动方案
                - 考虑是否需要使用不同的工具或方法
                
                **行动**：
                根据思考结果，选择并执行下一个具体的行动。如果任务已完成，请提供最终的总结和结果。
                
                ### 执行指导原则
                1. **渐进式推进**：每次行动都应该让任务向前推进一步
                2. **结果验证**：对每个行动的结果进行分析和验证
                3. **策略调整**：如果当前方法不奏效，及时调整策略
                4. **完整性检查**：确保解决方案的完整性和正确性
                5. **用户体验**：始终考虑最终用户的需求和体验
                
                ### 特殊情况处理
                - 如果遇到错误或异常，分析原因并提供替代方案
                - 如果需要更多信息，明确说明需要什么信息以及为什么需要
                - 如果任务复杂，可以将其分解为更小的子任务
                - 如果发现更好的解决方案，说明为什么它更优
                
                现在，请基于当前的执行记录继续你的任务。
                """;
        return nextPrompt;
    }
}