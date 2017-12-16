package com.affaince.subscription.metadata;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by anayonkar on 26/3/16.
 */
public final class ExecutionFlow {
    private String flowName;
    private List<ExecutionFlowNode> flowNodes;
    public ExecutionFlow() {

    }
    public ExecutionFlow(String flowName) {
        this.flowName = flowName;
        this.flowNodes = new LinkedList<>();
    }

    public String getFlowName() {
        return flowName;
    }

    public List<ExecutionFlowNode> getFlowNodes() {
        return flowNodes;
    }

    public void setFlowName(String flowName) {
        this.flowName = flowName;
    }

    public void setFlowNodes(List<ExecutionFlowNode> flowNodes) {
        this.flowNodes = flowNodes;
    }

    public boolean addExecutionFlowNode(ExecutionFlowNode executionFlowNode) {
        return this.flowNodes.add(executionFlowNode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExecutionFlow that = (ExecutionFlow) o;

        if (flowName != null ? !flowName.equals(that.flowName) : that.flowName != null) return false;
        return flowNodes != null ?
                flowNodes.equals(that.flowNodes) :
                that.flowNodes == null;

    }

    @Override
    public int hashCode() {
        int result = flowName != null ? flowName.hashCode() : 0;
        result = 31 * result + (flowNodes != null ? flowNodes.hashCode() : 0);
        return result;
    }

    /**
     *
     * @param firstFlow
     * @param secondFlow
     * @return true only if both sets are equal AND elements are in exact same order
     * (equals method of Set implementations does not check for ordering)
     */
    //not required as LinkedList is used instead of Set
    /*private static boolean equateFlows(Set<ExecutionFlowNode> firstFlow, Set<ExecutionFlowNode> secondFlow) {
        if(firstFlow == null || secondFlow == null) return false;
        if(firstFlow.size() != secondFlow.size()) return false;
        if(!firstFlow.equals(secondFlow)) return false;
        Iterator<ExecutionFlowNode> firstIterator = firstFlow.iterator();
        Iterator<ExecutionFlowNode> secondIterator = secondFlow.iterator();
        boolean result = true;
        while(result && firstIterator.hasNext() && secondIterator.hasNext()) {
            result &= firstIterator.next().equals(secondIterator.next());
        }
        return result;
    }*/
}
