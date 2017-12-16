package com.affaince.subscription.metadata;

/**
 * Created by anayonkar on 26/3/16.
 */
public final class ExecutionFlowNode {
    private String flowNodeName;
    //private String flowName;

    public ExecutionFlowNode() {

    }
    public ExecutionFlowNode(String flowNodeName/*, String flowName*/) {
        this.flowNodeName = flowNodeName;
        //this.flowName = flowName;
    }

    public String getFlowNodeName() {
        return flowNodeName;
    }

    /*public String getFlowName() {
        return flowName;
    }*/

    public void setFlowNodeName(String flowNodeName) {
        this.flowNodeName = flowNodeName;
    }

    /*public void setFlowName(String flowName) {
        this.flowName = flowName;
    }*/

    /*@Override
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
    }*/

}
