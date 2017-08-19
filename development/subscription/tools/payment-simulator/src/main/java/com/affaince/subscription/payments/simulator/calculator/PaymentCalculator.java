package com.affaince.subscription.payments.simulator.calculator;

/**
 * Created by rahul on 17/7/17.
 */
public interface PaymentCalculator {

    void setNextCalculator(PaymentCalculator nextCalculator);

    void calculate(PaymentInstallmentCalculationRequest request);
}
