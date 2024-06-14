package com.affaince.payments.scheme.compilation.units;

import com.affaince.payments.expressions.*;
import com.affaince.payments.scheme.PaymentsSchemeContext;
import com.affaince.payments.scheme.Scheme;
import com.affaince.payments.scheme.expressions.ResidualPaymentExpression;
import com.affaince.payments.vo.ComputedVariableUnit;
import com.affaince.payments.vo.VestingDistribution;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.List;

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class ResidualPayUnit {
    private ResidualPaymentExpression residualPaymentExpression;
    public ResidualPayUnit() {
    }

    public void setResidualPaymentExpression(ResidualPaymentExpression residualPaymentExpression) {
        this.residualPaymentExpression = residualPaymentExpression;
    }

    public Expression applyValuesToVariables(Expression expression,PaymentsSchemeContext paymentsSchemeContext){
        if( null != expression) {
            if (expression instanceof VariableExpression) {
                String variableName = (String) (expression.getLeftHandSide()).apply();
                Object variableValue = paymentsSchemeContext.searchVariableValue(variableName);
                if (null != variableValue) {
                    expression.setRightHandSide(new UnaryExpression(variableValue, UnaryType.NUMBER));
                }
            } else if( expression instanceof ArithmeticExpression) {
                if( null != expression.getLeftHandSide() ) {
                    expression.setLeftHandSide(applyValuesToVariables(expression.getLeftHandSide(), paymentsSchemeContext));
                }
                if(null != expression.getRightHandSide()) {
                    expression.setRightHandSide(applyValuesToVariables(expression.getRightHandSide(), paymentsSchemeContext));
                }
            }
        }
        return expression;

    }

    private ComputedVariableUnit computeDistributionValues(Expression expression,PaymentsSchemeContext paymentSchemeContext){
        expression = applyValuesToVariables(expression,paymentSchemeContext);
        Object variableKey =expression.apply();
        Object variableValue = expression.apply();
        return new ComputedVariableUnit(variableKey,variableValue);
    }
    private ComputedVariableUnit computeIntermediateValues(Expression expression,PaymentsSchemeContext paymentSchemeContext){
        expression = applyValuesToVariables(expression,paymentSchemeContext);
        Object variableKey =expression.getLeftHandSide().getLeftHandSide().apply();
        Object variableValue = expression.apply();
        return new ComputedVariableUnit(variableKey,variableValue);
    }
    public void execute(PaymentsSchemeContext paymentSchemeContext){
        List<Expression> expressionQueue = residualPaymentExpression.getExpressionQueue();
        for(Expression expression : expressionQueue){
            ComputedVariableUnit computedVariable = computeIntermediateValues(expression,paymentSchemeContext);
            paymentSchemeContext.addToComputedVariables((String)computedVariable.getVariableName(),(double)computedVariable.getVariableValue());
        }
         PaymentPrecedence paymentPrecedence = residualPaymentExpression.getPrecedence();
         if(paymentPrecedence==PaymentPrecedence.BEFORE){
             paymentSchemeContext.setBefore(true);
         }
         List<VestingDistribution> vestingDistributions = residualPaymentExpression.getVestingDistributions();
         for(VestingDistribution deliveryWiseBenefitTuple: vestingDistributions){
            ComputedVariableUnit key = computeDistributionValues(deliveryWiseBenefitTuple.getKey(),paymentSchemeContext);
            ComputedVariableUnit value = computeDistributionValues(deliveryWiseBenefitTuple.getValue(),paymentSchemeContext);
            paymentSchemeContext.addToPaymentVestingDistributionList((Double)key.getVariableValue(),(Double)value.getVariableValue());
         }
    }

    public void syncAllVariableReferences(Scheme scheme){
        this.residualPaymentExpression.syncAllVariableReferences(scheme);
    }

}
