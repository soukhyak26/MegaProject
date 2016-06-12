package com.subscription;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * Created by rbsavaliya on 30-04-2016.
 */
public class ArithmeticExpressionSpike {
    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();

//arithmetic operator
        System.out.println(parser.parseExpression("'Welcome SPEL'+'!'").getValue());
        System.out.println(parser.parseExpression("10 * 10/2").getValue());
        System.out.println(parser.parseExpression("'Today is: '+ new java.util.Date()").getValue());

//logical operator
        System.out.println(parser.parseExpression("true and true").getValue());

//Relational operator
        System.out.println(parser.parseExpression("'sonoo'.length()==5").getValue());

        System.out.println(parser.parseExpression("10 < 11 and 23 > 2").getValue());

        PointCalculationParameters pointCalculationParameters =
                new PointCalculationParameters(40000,12,3);
        ExpressionParser expressionParser = new SpelExpressionParser();
        Expression expression1 = expressionParser.parseExpression("((totalSubscription/totalPeriod)/2)*totalPoint");
        EvaluationContext context = new StandardEvaluationContext(pointCalculationParameters);
        double result = expression1.getValue(context, Double.class);
        System.out.println(result);
    }
}

class PointCalculationParameters {
    private double totalSubscription;
    private double totalPeriod;
    private double totalPoint;

    public PointCalculationParameters(double totalSubscription, double totalPeriod, double totalPoint) {
        this.totalSubscription = totalSubscription;
        this.totalPeriod = totalPeriod;
        this.totalPoint = totalPoint;
    }

    public double getTotalSubscription() {
        return totalSubscription;
    }

    public void setTotalSubscription(double totalSubscription) {
        this.totalSubscription = totalSubscription;
    }

    public double getTotalPeriod() {
        return totalPeriod;
    }

    public void setTotalPeriod(double totalPeriod) {
        this.totalPeriod = totalPeriod;
    }

    public double getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(double totalPoint) {
        this.totalPoint = totalPoint;
    }
}
