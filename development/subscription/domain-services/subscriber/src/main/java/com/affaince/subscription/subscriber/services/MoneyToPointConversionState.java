package com.affaince.subscription.subscriber.services;

import com.affaince.subscription.compiler.Rule;

/**
 * Created by rbsavaliya on 29-05-2016.
 */
public class MoneyToPointConversionState implements BenefitCalculationState {

    private final BenefitCalculationState nextState;
    public MoneyToPointConversionState(BenefitCalculationState state) {
        this.nextState=state;
    }

    @Override
    public void calculate(BenefitExecutionContext context) {

        final Rule rule = context.getApplicableBenefit();
        final BenefitCalculationRequest request = context.getRequest();
        final double currentSubscription = request.getCurrentSubscriptionAmount();
        final double rewardPoints = (currentSubscription/rule.getMoneyConversion().getCurrencyValue())*
                rule.getMoneyConversion().getPointValue();
        context.addRewardPoints (rewardPoints);
        if (nextState != null) {
            nextState.calculate(context);
        }
    }
}
