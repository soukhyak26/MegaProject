package com.affaince.subscription.subscriber.services;

import com.affaince.subscription.compiler.Rule;

/**
 * Created by rbsavaliya on 29-05-2016.
 */
public class BenefitExecutionContext {

    private BenefitCalculationRequest request;
    private BenefitCalculationState state;
    private Rule applicableBenefit;

    public BenefitResult calculateBenefit (BenefitCalculationRequest request) {
        return new BenefitResult();
    }

    public void setApplicableBenefit(Rule applicableBenefit) {
        this.applicableBenefit = applicableBenefit;
    }

    public Rule getApplicableBenefit() {
        return applicableBenefit;
    }
}
