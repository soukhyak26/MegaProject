package com.affaince.subscription.query.view;

import com.affaince.subscription.metadata.ExecutionFlow;

/**
 * Created by anayonkar on 10/4/16.
 */
public class CommonView {
    public static final String METADATA = "METADATA";
    private final String uuid;
    private final ExecutionFlow executionFlow;

    public CommonView(String uuid, String executionFlowName) {
        this.uuid = uuid;
        this.executionFlow = new ExecutionFlow(executionFlowName);
    }

    public String getUuid() {
        return uuid;
    }

    public ExecutionFlow getExecutionFlow() {
        return executionFlow;
    }
}
