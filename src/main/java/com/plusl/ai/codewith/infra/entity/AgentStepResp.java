package com.plusl.ai.codewith.infra.entity;

import lombok.Data;

import java.util.List;

@Data
public class AgentStepResp {
    private List<String> stepResults;

    public void addStepResult(Integer stepNum, String stepResult) {
        if (stepResults == null) {
            stepResults = new java.util.ArrayList<>();
        }
        stepResults.add("Step " + stepNum + ": " + stepResult);
    }

    public void addTerminateReason(String reason) {
        if (stepResults == null) {
            stepResults = new java.util.ArrayList<>();
        }
        stepResults.add("Terminated: " + reason);
    }
}
