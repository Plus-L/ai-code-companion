package com.plusl.ai.codewith.agent;

import com.plusl.ai.codewith.tool.Tool;
import com.plusl.ai.codewith.tool.ToolRegistry;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
public class ToolCallAgent extends ReActAgent{

    private List<Tool> tools;

    public ToolCallAgent() {
        super();
    }


    @Override
    public boolean think() {
        return false;
    }

    @Override
    public String act() {
        return "";
    }
}
