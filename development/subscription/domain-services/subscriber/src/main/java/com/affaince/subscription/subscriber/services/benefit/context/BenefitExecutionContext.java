package com.affaince.subscription.subscriber.services.benefit.context;

import com.affaince.subscription.compiler.Rule;
import com.affaince.subscription.subscriber.services.benefit.state.ApplicabilityState;
import com.affaince.subscription.subscriber.services.benefit.state.BenefitCalculationState;
import com.affaince.subscription.subscriber.services.benefit.state.EligibilityState;
import com.affaince.subscription.subscriber.services.benefit.state.PointConversionState;

/**
 * Created by rbsavaliya on 29-05-2016.
 */
public class BenefitExecutionContext {

    private BenefitCalculationRequest request;
    private BenefitCalculationState state;
    private Rule applicableBenefit;
    private double rewardPoints;
    private String benefitPayMethod;

    public BenefitResult calculateBenefit (BenefitCalculationRequest request) {
        BenefitCalculationState benefitCalculationState = new EligibilityState(
                new PointConversionState(
                        new ApplicabilityState(null)
                )
        );
        benefitCalculationState.calculate(this);
        return new BenefitResult(rewardPoints, benefitPayMethod);
    }

    public void setApplicableBenefit(Rule applicableBenefit) {
        this.applicableBenefit = applicableBenefit;
    }

    public Rule getApplicableBenefit() {
        return applicableBenefit;
    }

    public BenefitCalculationRequest getRequest() {
        return request;
    }

    public void addRewardPoints(double rewardPoints) {
        this.rewardPoints = this.rewardPoints + rewardPoints;
    }

    public void setBenefitPayMethod(String benefitPayMethod) {
        this.benefitPayMethod = benefitPayMethod;
    }
}