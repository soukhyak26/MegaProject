package com.affaince.subscription.subscriber.services;

import com.affaince.subscription.compiler.Rule;

/**
 * Created by rbsavaliya on 29-05-2016.
 */
public class PeriodToPointConversionState implements BenefitCalculationState {

    private final BenefitCalculationState nextState;
    public PeriodToPointConversionState(BenefitCalculationState state) {
        this.nextState=state;
    }

    @Override
    public void calculate(BenefitExecutionContext context) {
        final Rule rule = context.getApplicableBenefit();
        final BenefitCalculationRequest request = context.getRequest();
        final double currentSubscriptionPeriod = request.getCurrentSubscriptionPeriod();
        final double rewardPoints = (currentSubscriptionPeriod/rule.getPeriodConversion().getPeriodValue())
                *rule.getPeriodConversion().getPointValue();
        context.addRewardPoints(rewardPoints);
        if (nextState != null) {
            nextState.calculate(context);
        }
    }
}
