package payments.expressions;


import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ArithmeticComparisonExpression extends Expression {
    public ArithmeticComparisonExpression(){super();}
    public ArithmeticComparisonExpression(ArithmeticOperator operator, Expression leftHandSide, Expression rightHandSide) {
        super(operator, leftHandSide, rightHandSide);
    }

    private Object executeBiFunction(BiFunction<Expression,Expression, ?> biFunction) {
        return biFunction.apply(getLeftHandSide(), getRightHandSide());
    }



    private Object executeFunction(Function<Expression, ?> function) {
        return function.apply(getLeftHandSide());
    }


    public Object apply(){
        switch (this.getOperator()){
            case EQUALTO:
                BiFunction<Expression,Expression,?> equalTo = (a,b) -> ((Number)a.apply()).doubleValue() == ((Number)b.apply()).doubleValue();
                return executeBiFunction(equalTo);
            case LOOPEQUALTO:
                BiFunction<Expression,Expression,?> equalityInLoop =  (a,b)->((List<Number>)a.apply()).stream().allMatch(i-> i.doubleValue() == ((Number)b.apply()).doubleValue());
                return executeBiFunction(equalityInLoop);
            case GREATERTHAN:
                BiFunction<Expression,Expression,?> greaterThan = (a,b) -> ((Number)a.apply()).doubleValue() > ((Number)b.apply()).doubleValue();
                return executeBiFunction(greaterThan);
            case LOOPGREATERTHAN:
                BiFunction<Expression,Expression,?> greaterThanInLoop =  (a,b)->((List<Number>)a.apply()).stream().allMatch(i-> i.doubleValue() > ((Number)b.apply()).doubleValue());
                return executeBiFunction(greaterThanInLoop);
            case GREATERTHANEQUALTO:
                BiFunction<Expression,Expression,?> greaterThanEqualTo = (a,b) -> ((Number)a.apply()).doubleValue() >= ((Number)b.apply()).doubleValue();
                return executeBiFunction(greaterThanEqualTo);
            case LOOPGREATERTHANEQUALTO:
                BiFunction<Expression,Expression,?> greaterThanEqualToInLoop =  (a,b)->((List<Number>)a.apply()).stream().allMatch(i-> i.doubleValue() >= ((Number)b.apply()).doubleValue());
                return executeBiFunction(greaterThanEqualToInLoop);
            case LESSTHAN:
                BiFunction<Expression,Expression,?> lessThan = (a,b) -> ((Number)a.apply()).doubleValue() < ((Number)b.apply()).doubleValue();
                return executeBiFunction(lessThan);
            case LOOPLESSTHAN:
                BiFunction<Expression,Expression,?> lessThanInLoop =  (a,b)->((List<Number>)a.apply()).stream().allMatch(i-> i.doubleValue() < ((Number)b.apply()).doubleValue());
                return executeBiFunction(lessThanInLoop);
            case LESSTHANEQUALTO:
                BiFunction<Expression,Expression,?> lessThanEqualTo = (a,b) -> ((Number)a.apply()).doubleValue() <= ((Number)b.apply()).doubleValue() ;
                return executeBiFunction(lessThanEqualTo);
            case LOOPLESSTHANEQUALTO:
                BiFunction<Expression,Expression,?> lessThanEqualToInLoop =  (a,b)->((List<Number>)a.apply()).stream().allMatch(i-> i.doubleValue() <= ((Number)b.apply()).doubleValue());
                return executeBiFunction(lessThanEqualToInLoop);
            case NOTEQUALTO:
                BiFunction<Expression,Expression,?> notEqualTo = (a,b) -> ((Number)a.apply()).doubleValue() != ((Number)b.apply()).doubleValue();
                return executeBiFunction(notEqualTo);
            case LOOPNOTEQUALTO:
                BiFunction<Expression,Expression,?> notEqualToInLoop =  (a,b)->((List<Number>)a.apply()).stream().allMatch(i-> i.doubleValue() != ((Number)b.apply()).doubleValue());
                return executeBiFunction(notEqualToInLoop);
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
