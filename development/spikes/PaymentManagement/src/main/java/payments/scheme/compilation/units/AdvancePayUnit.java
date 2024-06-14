package payments.scheme.compilation.units;

import com.affaince.payments.expressions.*;
import com.affaince.payments.scheme.PaymentsSchemeContext;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class AdvancePayUnit {
    private List<Expression> expressionQueue;
    private String eventOfAdvancePayment;

    public AdvancePayUnit() {
        expressionQueue = new ArrayList<>();
    }

    public void addExpression(Expression expression) {
        this.expressionQueue.add(expression);
    }

    public List<Expression> getExpressionQueue() {
        return expressionQueue;
    }

    public void execute(PaymentsSchemeContext paymentSchemeContext){
        paymentSchemeContext.setEventOfAdvancePayment(this.eventOfAdvancePayment);
        for(Expression expression : expressionQueue){
            expression = applyValuesToVariables(expression,paymentSchemeContext);
            String variableName =(String)expression.getLeftHandSide().getLeftHandSide().apply();
            double variableValue = (double)expression.apply();
            paymentSchemeContext.addToComputedVariables(variableName,variableValue);
            paymentSchemeContext.setAdvancePaymentValue(variableValue);
        }
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

    public Expression searchVariableExpression(String variableName) {
        List<Expression> variableExpressionsQueue = this.getExpressionQueue();
        for (Expression expression : variableExpressionsQueue) {
            Expression foundExpression =searchVariableNameInExpression(variableName,expression);
            if(null!=foundExpression){
                return foundExpression;
            }
        }
        return null;
    }

    private Expression searchVariableNameInExpression(String variableName,Expression expression){
        if(null != expression) {
            if (expression instanceof VariableExpression) {
                if ((expression.getLeftHandSide().apply()).equals(variableName)) {
                    return expression;
                }
            } else {
                Expression foundExpression = searchVariableNameInExpression(variableName, expression.getLeftHandSide());
                if (null != foundExpression) {
                    return foundExpression;
                } else {
                    foundExpression = searchVariableNameInExpression(variableName, expression.getRightHandSide());
                    return foundExpression;
                }
            }
        }
        return null;
    }


    public void setEventOfAdvancePayment(String eventOfAdvancePayment) {
        this.eventOfAdvancePayment = eventOfAdvancePayment;
    }
}
