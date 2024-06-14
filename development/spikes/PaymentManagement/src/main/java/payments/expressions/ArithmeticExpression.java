package payments.expressions;


import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ArithmeticExpression extends Expression {
    public ArithmeticExpression(){super();}
    public ArithmeticExpression(ArithmeticOperator operator, Expression leftHandSide, Expression rightHandSide) {
        super(operator, leftHandSide, rightHandSide);
    }

    private Object executeBiFunction(BiFunction<Expression,Expression, ?> biFunction) {
        return biFunction.apply(getLeftHandSide(),getRightHandSide());
    }

    private Object executeFunction(Function<Expression,?> function) {
        return function.apply(getLeftHandSide());
    }

    public Object apply() {
        ArithmeticOperator arithmeticOperator = this.getOperator();
        switch (arithmeticOperator) {
            case ADDITION:
                BiFunction<Expression, Expression, ?> add = (a, b) -> (((Number)a.apply()).doubleValue() + ((Number)b.apply()).doubleValue());
                return executeBiFunction(add);
            case LOOPADDITION:
                Function<Expression,?> addInLoop = (a) -> ((List<Number>) a.apply()).stream().mapToDouble(i -> (i).doubleValue()).sum();
                return executeFunction(addInLoop);
            case SUBTRACTION:
                BiFunction<Expression, Expression, ?> sub = (a, b) -> ((Number)a.apply()).doubleValue() - ((Number)b.apply()).doubleValue();
                return executeBiFunction(sub);
            case DIVISION:
                BiFunction<Expression, Expression, ?> div = (a, b) -> ((Number)a.apply()).doubleValue() / ((Number)b.apply()).doubleValue();
                return executeBiFunction(div);
            case MULTIPLICATION:
                BiFunction<Expression, Expression, ?> mul = (a, b) -> ((Number)a.apply()).doubleValue() * ((Number)b.apply()).doubleValue();
                return executeBiFunction(mul);
            case MODULUS:
                BiFunction<Expression, Expression,?> mod = (a, b) -> ((Number)a.apply()).doubleValue() % ((Number)b.apply()).doubleValue();
                return executeBiFunction(mod);
            case TERNARY:
                ArithmeticComparisonExpression conditionalExpression = (ArithmeticComparisonExpression)this.getPreExpression();
                BiFunction<Expression, Expression, ?> ternary = (a, b) -> new UnaryExpression((Boolean)conditionalExpression.apply() ? ((Number) a.apply()).doubleValue() : ((Number) b.apply()).doubleValue(), UnaryType.NUMBER);
                if(this.getLeftHandSide().apply() instanceof Number) {
                    if(this.getRightHandSide().apply() instanceof  Number) {
                        ternary = (a, b) -> (Boolean)conditionalExpression.apply() ? ((Number) a.apply()).doubleValue() : ((Number) b.apply()).doubleValue();
                    }else if( this.getRightHandSide().apply() instanceof ArithmeticExpression){
                        ternary = (a, b) -> (Boolean)conditionalExpression.apply() ? ((Number) a.apply()).doubleValue() : ((ArithmeticExpression) b.apply());
                    }
                }else if(this.getLeftHandSide().apply() instanceof ArithmeticExpression){
                    if(this.getRightHandSide().apply() instanceof  Number) {
                        ternary = (a, b) -> (Boolean)conditionalExpression.apply() ? ((ArithmeticExpression) a.apply()) : ((Number) b.apply()).doubleValue();
                    }else if( this.getRightHandSide().apply() instanceof ArithmeticExpression){
                        ternary = (a, b) -> (Boolean)conditionalExpression.apply() ? ((ArithmeticExpression) a.apply()) : ((ArithmeticExpression) b.apply());
                    }
                }
                return executeBiFunction(ternary);
            case ASSIGN:
                BiFunction<Expression, Expression,?> assign = (a, b) -> ((Number)b.apply()).doubleValue();
                return executeBiFunction(assign);
            default:
                throw new IllegalStateException("Unexpected value: " + this.getOperator());
        }
    }

}
