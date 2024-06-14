package com.affaince.payments.scheme.expressions;

import com.affaince.payments.expressions.Expression;
import com.affaince.payments.expressions.PaymentPrecedence;
import com.affaince.payments.scheme.Scheme;
import com.affaince.payments.vo.VestingDistribution;

import java.util.ArrayList;
import java.util.List;

public class ResidualPaymentExpression {
    private List<Expression> expressionQueue;
    private PaymentPrecedence precedence;
    private List<Expression>  vestingPeriodicityExpressions;
    private List<VestingDistribution> vestingDistributions;

    public ResidualPaymentExpression(){
        expressionQueue = new ArrayList<>();
        vestingPeriodicityExpressions = new ArrayList<>();
        vestingDistributions = new ArrayList<>();
    }

    public void addExpression(Expression expression) {
        this.expressionQueue.add(expression);
    }
    public Expression searchOutputVariableInExpressionQueue(String variableName){
        for(Expression expression : expressionQueue){
            Expression variableExpression = expression.searchVariableOnLHS(variableName);
            if(null !=variableExpression){
                return variableExpression;
            }
        }
        return null;
    }

    public void syncAllVariableReferences(Scheme scheme) {
        for(Expression vestingPeriodicityExpression : vestingPeriodicityExpressions){
            Expression valuedVariableExpression = scheme.searchVariableExpression((String)vestingPeriodicityExpression.getRightHandSide().getLeftHandSide().apply());
            if(null != valuedVariableExpression){
                vestingPeriodicityExpression.getRightHandSide().setRightHandSide(valuedVariableExpression.getRightHandSide());
            }
        }
        for(VestingDistribution vestingDistributionExpressionEntry : vestingDistributions){
            Expression vestingPeriodicityExpression = vestingDistributionExpressionEntry.getKey();
            Expression valuedVariableExpression = scheme.searchVariableExpression((String)vestingPeriodicityExpression.getRightHandSide().getLeftHandSide().apply());
            if(null != valuedVariableExpression){
                vestingPeriodicityExpression.getRightHandSide().setRightHandSide(valuedVariableExpression.getRightHandSide());
            }
            Expression arithmeticExpression = vestingDistributionExpressionEntry.getValue();
            Expression payVariableExpression = arithmeticExpression.getRightHandSide();
            valuedVariableExpression = scheme.searchVariableExpression((String)payVariableExpression.getLeftHandSide().apply());
            if(null != valuedVariableExpression){
                payVariableExpression.getLeftHandSide().setRightHandSide(valuedVariableExpression.getRightHandSide());
            }
        }
    }

    public List<Expression> getExpressionQueue() {
        return expressionQueue;
    }

    public PaymentPrecedence getPrecedence() {
        return precedence;
    }

    public List<Expression> getVestingPeriodicityExpressions() {
        return vestingPeriodicityExpressions;
    }


    public void setPrecedence(PaymentPrecedence precedence) {
        this.precedence = precedence;
    }


    public void setVestingPeriodicityExpressions(List<Expression> vestingPeriodicityExpressions) {
        this.vestingPeriodicityExpressions = vestingPeriodicityExpressions;
    }

    public List<VestingDistribution> getVestingDistributions() {
        return vestingDistributions;
    }

    public void setVestingDistributions(List<VestingDistribution> vestingDistributions) {
        this.vestingDistributions = vestingDistributions;
    }


}
