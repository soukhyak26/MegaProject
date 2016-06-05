package com.subscription;

import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

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
    }
}
