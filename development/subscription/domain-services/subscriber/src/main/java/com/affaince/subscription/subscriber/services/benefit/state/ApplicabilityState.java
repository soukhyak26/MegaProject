package com.affaince.subscription.subscriber.services.benefit.state;

import com.affaince.subscription.compiler.Rule;
import com.affaince.subscription.subscriber.services.benefit.context.BenefitExecutionContext;

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

        if (nextState != null) {
            nextState.calculate(context);
        }
    }
}
