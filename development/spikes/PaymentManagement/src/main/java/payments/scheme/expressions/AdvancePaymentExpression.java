package payments.scheme.expressions;

import com.affaince.payments.expressions.Expression;
import com.affaince.payments.scheme.Scheme;

//PAY 40% OF CURRENT SUBSCRIPTION AMOUNT ON SUBSCRIPTION CONFIRMATION
public class AdvancePaymentExpression {
    private Expression paymentShare; //40%
    private Expression multiplierVariable;
    private Expression deliveriesCountOptionalVariable;
    private String eventOfAdvancePayment;
    private Expression advancePaymentArithmeticExpression;

    public AdvancePaymentExpression() {
    }

    public void syncAllVariableReferences(Scheme scheme) {
        //payableVariable
        Expression valuedVariableExpression = scheme.searchVariableExpression((String) paymentShare.getLeftHandSide().apply());
        if (null != valuedVariableExpression) {
            paymentShare.setRightHandSide(valuedVariableExpression.getRightHandSide());
        }
        valuedVariableExpression = scheme.searchVariableExpression((String) multiplierVariable.getLeftHandSide().apply());
        if (null != valuedVariableExpression) {
            multiplierVariable.setRightHandSide(valuedVariableExpression.getRightHandSide());
        }
        //to be dealt later
        //deliveriesCountOptionalVariable = deliveriesCountOptionalVariable.getLeftHandSide();
    }

    public Expression getPaymentShare() {
        return paymentShare;
    }

    public void setPaymentShare(Expression paymentShare) {
        this.paymentShare = paymentShare;
    }


    public Expression getMultiplierVariable() {
        return multiplierVariable;
    }

    public void setMultiplierVariable(Expression multiplierVariable) {
        this.multiplierVariable = multiplierVariable;
    }

    public Expression getDeliveriesCountOptionalVariable() {
        return deliveriesCountOptionalVariable;
    }

    public void setDeliveriesCountOptionalVariable(Expression deliveriesCountOptionalVariable) {
        this.deliveriesCountOptionalVariable = deliveriesCountOptionalVariable;
    }

    public String getEventOfAdvancePayment() {
        return eventOfAdvancePayment;
    }

    public void setEventOfAdvancePayment(String eventOfAdvancePayment) {
        this.eventOfAdvancePayment = eventOfAdvancePayment;
    }

    public Expression getAdvancePaymentArithmeticExpression() {
        return advancePaymentArithmeticExpression;
    }

    public void setAdvancePaymentArithmeticExpression(Expression advancePaymentArithmeticExpression) {
        this.advancePaymentArithmeticExpression = advancePaymentArithmeticExpression;
    }

}
