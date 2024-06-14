package payments.scheme.compilation.units;

import com.affaince.payments.expressions.Expression;
import com.affaince.payments.scheme.PaymentsSchemeContext;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id")
public class GivenUnit {
    private List<Expression> expressionQueue;

    public GivenUnit() {
        this.expressionQueue = new ArrayList<>();
    }

    public void addExpression(Expression expression){
        String variableName = expression.getLeftHandSide().apply().toString();
        boolean isAlreadyExist = expressionQueue.stream().anyMatch(exp->exp.getLeftHandSide().apply().toString().equals(variableName)) ;
        if(! isAlreadyExist) {
            this.expressionQueue.add(expression);
        }else{
            expressionQueue = expressionQueue.stream().
                    map(exp->exp.getLeftHandSide().apply().toString().equals(variableName) ? expression : exp).
                    collect(Collectors.toList());
        }
    }

    public List<Expression> getExpressionQueue() {
        return expressionQueue;
    }

    public void execute(PaymentsSchemeContext paymentsSchemeContext){
        List<Expression> queue = this.getExpressionQueue();
        String variableName=null;
        Object value=null;
        for (Expression expression : queue) {
            if(null == expression.getRightHandSide()) {
                variableName = (String) expression.getLeftHandSide().apply();
                value = paymentsSchemeContext.getMetricsContext().findValue(variableName);
               // VariableExpression variableExpression = new VariableExpression(new VariableIdentifierExpression(variableName), new UnaryExpression(value, UnaryExpression.obtainUnaryType(Object.class)));
                //this.addExpression(variableExpression);
            }else{
                variableName = (String) expression.getLeftHandSide().apply();
                value = expression.getRightHandSide().apply();
            }

            if (null != value) {
                paymentsSchemeContext.addToInputVariables(variableName, value);
            }
        }
    }

    public Expression searchVariableExpression(String variableName) {
        List<Expression> variableExpressionsQueue = this.getExpressionQueue();
        for(Expression expression: variableExpressionsQueue){
            String inputVariableName= (String)expression.getLeftHandSide().apply();
            if(inputVariableName.equals(variableName)){
                return expression;
            }
        }
        return null;
    }


}
