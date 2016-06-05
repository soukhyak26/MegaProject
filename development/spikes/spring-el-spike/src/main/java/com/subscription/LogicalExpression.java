package com.subscription;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Created by rbsavaliya on 05-06-2016.
 */
public class LogicalExpression {
    public static void main (String [] args) {
        Parameters parameters = new Parameters(2000, 100000);
        ExpressionParser expressionParser = new SpelExpressionParser();
        //Expression expression = expressionParser.parseExpression("totalProfit");
        //expressionParser.parseExpression("totalSubscription");
        Expression expression1 = expressionParser.parseExpression("totalProfit > 1000 or totalSubscription < 50000");
        EvaluationContext context = new StandardEvaluationContext(parameters);
        boolean result = expression1.getValue(context, Boolean.class);
        System.out.println(result);
    }
}

class Parameters {
    private double totalProfit;
    private double totalSubscription;

    public Parameters(double totalProfit, double totalSubscription) {
        this.totalProfit = totalProfit;
        this.totalSubscription = totalSubscription;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public double getTotalSubscription() {
        return totalSubscription;
    }

    public void setTotalSubscription(double totalSubscription) {
        this.totalSubscription = totalSubscription;
    }
}
