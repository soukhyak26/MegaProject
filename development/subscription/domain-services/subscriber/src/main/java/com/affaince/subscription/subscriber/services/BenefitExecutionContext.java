package com.affaince.subscription.subscriber.services;

/**
 * Created by rbsavaliya on 29-05-2016.
 */
public class BenefitExecutionContext {

    private BenefitCalculationRequest request;
    private BenefitCalculationState state;

    public BenefitResult calculateBenefit (BenefitCalculationRequest request) {
        return new BenefitResult();
    }
}
