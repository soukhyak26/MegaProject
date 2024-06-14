package com.affaince.payments.expressions;


import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class StringComparisonExpression extends Expression {
    public StringComparisonExpression(){super();}
    public StringComparisonExpression(ArithmeticOperator operator, Expression leftHandSide, Expression rightHandSide) {
        super(operator, leftHandSide, rightHandSide);
    }


    private Object executeBiFunction(BiFunction<Expression,Expression,?> biFunction){
        return biFunction.apply(getLeftHandSide(), getRightHandSide());
    }
    private Object executeFunction(Function<Expression, ?> function) {
        return function.apply(getLeftHandSide());
    }

    public Object apply(){
        switch (this.getOperator()){
            case EQUALTO:
                BiFunction<Expression,Expression,?> equalTo = (a,b) -> ((String)(a.apply())).equals((String)(b.apply())) ;
                return executeBiFunction(equalTo);
            case NOTEQUALTO:
                BiFunction<Expression,Expression,?> notEqualTo = (a,b) ->!((String)(a.apply())).equals((String)(b.apply())) ;
                return executeBiFunction(notEqualTo);
            case AND:
                Function<Expression,?> andExpressions = (a) -> ((List<Expression>)(a.apply()))
                        .stream().map(element->(Boolean)element.apply())
                        .reduce((p,q)-> p && q).orElse(false) ;   //.stream().reduce(Boolean::logicalAnd).orElse(false);
                return executeFunction(andExpressions);
            case OR :
                Function<Expression,?> orExpressions = (a) -> ((List<Expression>)(a.apply()))
                        .stream().map(element->(Boolean)element.apply())
                        .reduce((p,q)-> p || q).orElse(false) ;   //.stream().reduce(Boolean::logicalAnd).orElse(false);

                return executeFunction(orExpressions);
            default:
                throw new IllegalStateException("Unexpected value: " + this.getOperator());
        }
    }

}
