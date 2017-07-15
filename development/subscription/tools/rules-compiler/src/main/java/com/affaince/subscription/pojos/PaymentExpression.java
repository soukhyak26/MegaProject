package com.affaince.subscription.pojos;

/**
 * Created by rahul on 15/7/17.
 */
public class PaymentExpression {

    private AdvancePaymentParameters advancePaymentParameters;
    private ResidualDuePaymentParameters residualDuePaymentParameters;

    public PaymentExpression(AdvancePaymentParameters advancePaymentParameters, ResidualDuePaymentParameters residualDuePaymentParameters) {
        this.advancePaymentParameters = advancePaymentParameters;
        this.residualDuePaymentParameters = residualDuePaymentParameters;
    }

    public PaymentExpression() {
    }

    public AdvancePaymentParameters getAdvancePaymentParameters() {
        return advancePaymentParameters;
    }

    public void setAdvancePaymentParameters(AdvancePaymentParameters advancePaymentParameters) {
        this.advancePaymentParameters = advancePaymentParameters;
    }

    public ResidualDuePaymentParameters getResidualDuePaymentParameters() {
        return residualDuePaymentParameters;
    }

    public void setResidualDuePaymentParameters(ResidualDuePaymentParameters residualDuePaymentParameters) {
        this.residualDuePaymentParameters = residualDuePaymentParameters;
    }
}
