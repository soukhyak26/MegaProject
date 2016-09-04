package com.affaince.subscription.subscriber.services.benefit.state;

import com.affaince.subscription.subscriber.services.benefit.context.BenefitExecutionContext;

/**
 * Created by rbsavaliya on 29-05-2016.
 */
public class CalculationState implements BenefitCalculationState {

    private final BenefitCalculationState nextState;
    public CalculationState (BenefitCalculationState state) {
        this.nextState=state;
    }

    @Override
    public void calculate(BenefitExecutionContext context) {
        String calculationEquation = context.getApplicableBenefit().getEligibilityCondition();
        if (nextState != null) {
            nextState.calculate(context);
        }
    }
}
