package payments.processors.reg;

import com.affaince.payments.scheme.Scheme;


public class AdvancePaymentExpressionBuilder {
    private final Scheme scheme;
    public AdvancePaymentExpressionBuilder(Scheme scheme){
        this.scheme=scheme;
    }
/*
    public AdvancePaymentExpression buildExpression(PaymentsParser.AdvancePayUnitContext advancePayUnitContext) {
        return processExpression(advancePayUnitContext);
    }


    private AdvancePaymentExpression processExpression(PaymentsParser.AdvancePayUnitContext advancePayUnitContext) {
        AdvancePaymentExpression advancePaymentExpression = new AdvancePaymentExpression();
        advancePaymentExpression.setPaymentShare(buildPaymentShare(advancePayUnitContext));
        advancePaymentExpression.setMultiplierVariable(buildMultiplierVariable(advancePayUnitContext));
        advancePaymentExpression.setEventOfAdvancePayment(obtainEventOfAdvancePayment(advancePayUnitContext));

        Expression advancePaymentArithmeticExpression =buildAdvancePaymentArithmeticExpression(scheme,advancePaymentExpression);
        advancePaymentExpression.setAdvancePaymentArithmeticExpression(advancePaymentArithmeticExpression);
        return advancePaymentExpression;
    }

    private Expression buildPaymentShare(PaymentsParser.AdvancePayUnitContext advancePayUnitContext){
        TerminalNode paymentShare = advancePayUnitContext.paymentShare().NUMBER();
        return new UnaryExpression(Integer.parseInt(paymentShare.getText()),UnaryType.NUMBER);
    }
    private Expression buildMultiplierVariable(PaymentsParser.AdvancePayUnitContext advancePayUnitContext){
        return scheme.searchVariableExpression(advancePayUnitContext.variableName().getText());
    }
    private String obtainEventOfAdvancePayment(PaymentsParser.AdvancePayUnitContext advancePayUnitContext){
        return advancePayUnitContext.ADVANCE_PAYMENT_EVENT().getText();
    }

    private Expression buildAdvancePaymentArithmeticExpression(Scheme scheme, AdvancePaymentExpression advancePaymentExpression){
        Expression multiplierVariable = scheme.searchVariableExpression((String)advancePaymentExpression.getMultiplierVariable().getLeftHandSide().apply());
        return new ArithmeticExpression(ArithmeticOperator.MULTIPLICATION,multiplierVariable,
                new ArithmeticExpression(ArithmeticOperator.DIVISION, advancePaymentExpression.getPaymentShare(),new UnaryExpression(100,UnaryType.NUMBER)));
    }
*/
}
