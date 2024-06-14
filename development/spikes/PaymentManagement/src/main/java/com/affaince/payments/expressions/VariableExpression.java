package com.affaince.payments.expressions;

public class VariableExpression extends Expression  {
    public VariableExpression(){super();}
    public VariableExpression(Expression variableName,Expression variable) {
        super(ArithmeticOperator.ASSIGN,variableName,variable);
    }

    public Object apply() {
            if(null != getRightHandSide()) {
                return getRightHandSide().apply();
            }
            return null;
    }
}
