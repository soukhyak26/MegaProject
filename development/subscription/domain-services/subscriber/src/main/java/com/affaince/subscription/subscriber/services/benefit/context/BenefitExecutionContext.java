package com.affaince.subscription.subscriber.services.benefit.context;

import com.affaince.subscription.compiler.Rule;
import com.affaince.subscription.subscriber.services.benefit.state.ApplicabilityState;
import com.affaince.subscription.subscriber.services.benefit.state.BenefitCalculationState;
import com.affaince.subscription.subscriber.services.benefit.state.EligibilityState;
import com.affaince.subscription.subscriber.services.benefit.state.PointConversionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by rbsavaliya on 29-05-2016.
 */
@Component
public class BenefitExecutionContext {

    private BenefitCalculationRequest request;
    private BenefitCalculationState state;
    private Rule applicableBenefit;
    private double rewardPoints;
    private String benefitPayMethod;
    private Map<String, Double> rewardPointsDistribution;
    @Autowired
    private BenefitCalculationState benefitCalculationState;

    public BenefitResult calculateBenefit (BenefitCalculationRequest request) {
        benefitCalculationState.calculate(this);
        return new BenefitResult(rewardPoints, benefitPayMethod, rewardPointsDistribution);
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

    public double getRewardPoints() {
        return rewardPoints;
    }

    public Map<String, Double> getRewardPointsDistribution() {
        return rewardPointsDistribution;
    }

    public void setRewardPointsDistribution(Map<String, Double> rewardPointsDistribution) {
        this.rewardPointsDistribution = rewardPointsDistribution;
    }
}
