package com.affaince.payments.vo;

public class ComputedVariableUnit {
    Object variableName;
    Object variableValue;

    public ComputedVariableUnit(Object variableName, Object variableValue) {
        this.variableName = variableName;
        this.variableValue = variableValue;
    }

    public Object getVariableName() {
        return variableName;
    }

    public Object getVariableValue() {
        return variableValue;
    }
}
