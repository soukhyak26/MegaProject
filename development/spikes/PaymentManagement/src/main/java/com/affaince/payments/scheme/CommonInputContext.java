package com.affaince.payments.scheme;

import com.affaince.payments.expressions.VariableExpression;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.List;

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class CommonInputContext {
    private List<VariableExpression> inputVariables;
    public CommonInputContext(){}
    public CommonInputContext(List<VariableExpression> inputVariables){
        this.inputVariables=inputVariables;
    }

    public List<VariableExpression> getInputVariables() {
        return inputVariables;
    }
}
