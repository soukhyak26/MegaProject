package com.affaince.subscription.subscriber.services.benefit.context;

import java.util.Map;

/**
 * Created by rbsavaliya on 29-05-2016.
 */
public class BenefitResult {

    private double rewardPoints;
    private String benefitPayMethod;
    private Map<String, Double> rewardPointsDistribution;

    public BenefitResult(double rewardPoints, String benefitPayMethod, Map<String, Double> rewardPointsDistribution) {
        this.rewardPoints = rewardPoints;
        this.benefitPayMethod = benefitPayMethod;
        this.rewardPointsDistribution = rewardPointsDistribution;
    }

    public double getRewardPoints() {
        return rewardPoints;
    }

    public String getBenefitPayMethod() {
        return benefitPayMethod;
    }

    public Map<String, Double> getRewardPointsDistribution() {
        return rewardPointsDistribution;
    }
}
