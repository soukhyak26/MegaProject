package payments.expressions;

import java.util.function.BiFunction;
import java.util.function.Function;

public class StringExpression extends Expression  {
    public StringExpression(){super();}
    public StringExpression(ArithmeticOperator operator, Expression leftHandSide, Expression rightHandSide) {
        super(operator, leftHandSide, rightHandSide);
    }
    private Object executeBiFunction(BiFunction<Expression,Expression,?> biFunction) {
        return biFunction.apply(getLeftHandSide(),getRightHandSide());
    }
    private Object executeFunction(Function<Expression,?> function) {
        return function.apply(getLeftHandSide());
    }

    @Override
    public Object apply() {
        ArithmeticOperator arithmeticOperator = this.getOperator();
        switch (arithmeticOperator) {
            case STRCONCAT:
                BiFunction<Expression, Expression,?> concat = (a, b) -> a.apply().toString().concat(b.apply().toString());
                return executeBiFunction(concat);
            case STRUPPERCASE:
                Function<Expression,?> upperCase = (a) -> a.apply().toString().toUpperCase();
                return executeFunction(upperCase);
            case STRLOWERCASE:
                Function<Expression,?> lowerCase = (a) -> a.apply().toString().toLowerCase();
                return executeFunction(lowerCase);
            default:
                throw new IllegalStateException("Unexpected value: " + this.getOperator());

        }
    }
}
