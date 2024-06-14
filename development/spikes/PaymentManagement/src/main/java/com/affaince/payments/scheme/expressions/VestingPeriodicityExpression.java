package com.affaince.payments.scheme.expressions;

import com.affaince.payments.expressions.Expression;

public class VestingPeriodicityExpression {
    private Expression arithmeticExpression;
    private Expression multiplierVariable;


    public Expression getArithmeticExpression() {
        return arithmeticExpression;
    }

    public void setArithmeticExpression(Expression arithmeticExpression) {
        this.arithmeticExpression = arithmeticExpression;
    }

    public Expression getMultiplierVariable() {
        return multiplierVariable;
    }

    public void setMultiplierVariable(Expression multiplierVariable) {
        this.multiplierVariable = multiplierVariable;
    }
}
