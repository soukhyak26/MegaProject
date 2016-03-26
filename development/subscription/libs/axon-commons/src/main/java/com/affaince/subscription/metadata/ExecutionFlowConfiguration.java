package com.affaince.subscription.metadata;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anayonkar on 26/3/16.
 */
public final class ExecutionFlowConfiguration {
    private final Map<String, ExecutionFlow> flowConfiguration;
    private ExecutionFlowConfiguration() {
        flowConfiguration = new HashMap<>();
    }
    private static class ExecutionFlowConfigurationHolder {
        private static final ExecutionFlowConfiguration INSTANCE = new ExecutionFlowConfiguration();
    }
    public static ExecutionFlowConfiguration getInstance() {
        return ExecutionFlowConfigurationHolder.INSTANCE;
    }
    public void initConfiguration() {
        //TODO: Read configuration xml and populate flow map
    }
}
