package com.affaince.subscription.subscriber.services;

import com.affaince.subscription.compiler.Conclusion;

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

        final Conclusion conclusion = context.getApplicableBenefit().getApplicability();
        if (context.getRequest().getAdvancePaymentPercent() >
                conclusion.getAdvancePaymentPercentage()) {
            context.setPeriodOption(conclusion.getPeriodOption());
            context.setBenefitPayMethod(conclusion.getBenefitPayMethod());
            context.setBenefitPaymentFrequency(conclusion.getBenefitPaymentFrequency());
            nextState.calculate(context);
        } else {
            return;
        }
    }
}
