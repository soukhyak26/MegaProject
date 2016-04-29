package com.affaince.subscription.metadata;

import groovy.transform.ASTTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by anayonkar on 18/4/16.
 */
//https://web.archive.org/web/20140220002834/http://isagoksu.com/2009/development/agile-development/test-driven-development/using-junit-parameterized-annotation/
//http://www.mkyong.com/unittest/junit-4-tutorial-6-parameterized-test/
    @Ignore
@RunWith(Parameterized.class)
public class ExecutionFlowParameterizedTest {
    private static final String FLOW_PREFIX = "SampleFlow_";
    private static final String NODE_PREFIX = "SampleStep_";
    private static final String ERROR_MESSAGE = "Error Message";
    private Integer flowIndex;
    private Integer nodeIndex;
    private Boolean validationResult;
    private Map<String, ExecutionFlow> flowConfiguration;

    public ExecutionFlowParameterizedTest(Integer flowIndex, Integer nodeIndex, Boolean validationResult) {
        this.flowIndex = flowIndex;
        this.nodeIndex = nodeIndex;
        this.validationResult = validationResult;
    }

    @Before
    public void init() {
        flowConfiguration = ExecutionFlowConfiguration.getInstance().getFlowConfiguration();
    }

    @Parameterized.Parameters
    public static Collection executionFlows() {
        return Arrays.asList(new Object[][]{
                {0,0,true},
                {0,1,true},
                {0,2,true},
                {0,3,true},
                {0,4,true},
                {0,5,false},//negative
                {1,0,true},
                {1,1,true},
                {1,2,true},
                {1,3,true},
                {1,4,true},
                {1,5,false},//negative
                {2,0,false},//negative
                {2,1,false},//negative
                {3,0,false},//negative
                {3,1,false},//negative
                {4,-4,false},//negative
                {-5,5,false},//negative
                {-6,-6,false}//negative
        });
    }

    @Test
    public void testFlow() {
        if(flowIndex < 0 || nodeIndex < 0) {
            Assert.assertEquals(ERROR_MESSAGE, validationResult, false);
            return;
        }
        String flowName = FLOW_PREFIX.concat(Integer.valueOf(flowIndex + 1).toString());
        String nodeName = NODE_PREFIX.concat(Integer.valueOf(flowIndex + 1).toString())
                            .concat(Integer.valueOf(nodeIndex + 1).toString());
        System.out.println("Execution test with flowName = "
                + flowName
                + " ; flowIndex = "
                + flowIndex
                + " ; nodeName = "
                + nodeName
                + " ; nodeIndex = "
                + nodeIndex
                + " ; validationResult = "
                + validationResult);
        ExecutionFlow executionFlow = flowConfiguration.get(flowName);
        if(executionFlow == null) {
            Assert.assertEquals(ERROR_MESSAGE, validationResult, false);
            return;
        }
        List<ExecutionFlowNode> executionFlowNodes = executionFlow.getFlowNodes();
        if(executionFlowNodes == null || executionFlowNodes.size() < (nodeIndex + 1)) {
            Assert.assertEquals(ERROR_MESSAGE, validationResult, false);
            return;
        }
        Assert.assertEquals(ERROR_MESSAGE,
                executionFlowNodes.get(nodeIndex).getFlowName().equals(flowName)
                    && executionFlowNodes.get(nodeIndex).getFlowNodeName().equals(nodeName),
                true);
    }

}
