package com.plusl.ai.codewith.infra.common;

/**
 * AgentState represents the possible states of an agent in the system.
 * Each state indicates the current operational status of the agent.
 *
 * @author PlusL
 * @since 0.0.1
 */
public enum AgentState {
    
    /**
     * The agent is idle and waiting for tasks.
     */
    IDLE,
    
    /**
     * The agent is currently working on a task.
     */
    RUNNING,
    
    /**
     * The agent has completed its task successfully.
     */
    COMPLETED,
    
    /**
     * The agent has failed to complete its task.
     */
    ERROR
}
