package com.affaince.subscription.benefit.simulator.benefit.state;

import com.affaince.subscription.benefit.simulator.benefit.context.BenefitExecutionContext;
import com.affaince.subscription.compiler.Rule;

/**
 * Created by rbsavaliya on 29-05-2016.
 */
public class ApplicabilityState implements BenefitCalculationState {

    private final BenefitCalculationState nextState;
    public ApplicabilityState (BenefitCalculationState state) {
        this.nextState=state;
    }

    @Override
    public void calculate(BenefitExecutionContext context) {

        final Rule rule = context.getApplicableBenefit();
        context.setBenefitPayMethod(rule.getBenefitPaymentMethod());
        PaymentStrategy paymentStrategy;
        if (rule.getBenefitPaymentMethod().equals("incremental")) {
            paymentStrategy = new IncrementalPaymentStrategy();
        } else if (rule.getBenefitPaymentMethod().equals("on_delivery_size")) {
            paymentStrategy = new OnDeliverySizePaymentStrategy();
        } else {
            paymentStrategy = new BenefitEquationPaymentStrategy();
        }
        paymentStrategy.distributeRewardPoints(context);
        if (nextState != null) {
            nextState.calculate(context);
        }
    }
}
