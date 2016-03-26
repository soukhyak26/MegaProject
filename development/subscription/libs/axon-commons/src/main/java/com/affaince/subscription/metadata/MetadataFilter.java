package com.affaince.subscription.metadata;

import java.util.*;

/**
 * Created by anayonkar on 20/3/16.
 */
public final class MetadataFilter {
    public static final String FLOW_ID = "FLOW_ID";
    public static final String TIMESTAMP = "TIMESTAMP";
    public static final String METADATA = "METADATA";
    //public static final String USER_ID = "USER_ID";
   /* private final String flowName;
    private final List<String> flowDefinition;
    private final Set<String> executedSteps;*/
    private final String uuid;
    private final ExecutionFlow executionFlow;
    private final Map<String,String> metadataValues = new HashMap<>();

    public MetadataFilter(String uuid, ExecutionFlow executionFlow) {
        this.uuid = uuid;
        this.executionFlow = executionFlow;
    }

    public String getUuid() {
        return uuid;
    }

    public ExecutionFlow getExecutionFlow() {
        return executionFlow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MetadataFilter that = (MetadataFilter) o;

        if (uuid != null ? !uuid.equals(that.uuid) : that.uuid != null) return false;
        return executionFlow != null ? executionFlow.equals(that.executionFlow) : that.executionFlow == null;

    }

    @Override
    public int hashCode() {
        int result = uuid != null ? uuid.hashCode() : 0;
        result = 31 * result + (executionFlow != null ? executionFlow.hashCode() : 0);
        return result;
    }
    /*public MetadataFilter(ExecutionFlow executionFlow) {
        this.executionFlow = executionFlow;
    }

    public ExecutionFlow getExecutionFlow() {
        return executionFlow;
    }*/
    /*public MetadataFilter(String flowName) {
        this.flowName = flowName;
        this.flowDefinition = new ArrayList<>();
        this.executedSteps = new HashSet<>();
    }*/
    //private Map<String, Object> metadataMap = new HashMap<>();

    /*public MetadataFilter() {
    }*/

    /*public Map<String, Object> getMetadataMap() {
        return metadataMap;
    }

    public void setMetadataMap(Map<String, Object> metadataMap) {
        this.metadataMap = metadataMap;
    }*/

    public Map<String, String> getMetadataValues() {
        return metadataValues;
    }
}
