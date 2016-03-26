package com.affaince.subscription.metadata;

/**
 * Created by anayonkar on 26/3/16.
 */
public final class ExecutionFlowNode {
    private final String flowNodeName;
    private final String flowName;
    /*private final ExecutionFlowNode before;
    private final ExecutionFlowNode after;*/

    public ExecutionFlowNode(String flowNodeName, String flowName/*, ExecutionFlowNode before, ExecutionFlowNode after*/) {
        this.flowNodeName = flowNodeName;
        this.flowName = flowName;
        /*this.before = before;
        this.after = after;*/
    }

    public String getFlowNodeName() {
        return flowNodeName;
    }

    public String getFlowName() {
        return flowName;
    }

    /*public ExecutionFlowNode getBefore() {
        return before;
    }

    public ExecutionFlowNode getAfter() {
        return after;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExecutionFlowNode that = (ExecutionFlowNode) o;

        if (flowNodeName != null ? !flowNodeName.equals(that.flowNodeName) : that.flowNodeName != null) return false;
        return flowName != null ? flowName.equals(that.flowName) : that.flowName == null;

    }

    @Override
    public int hashCode() {
        int result = flowNodeName != null ? flowNodeName.hashCode() : 0;
        result = 31 * result + (flowName != null ? flowName.hashCode() : 0);
        return result;
    }
}
