package com.affaince.subscription.subscriber.services.benefit.context;

/**
 * Created by rbsavaliya on 29-05-2016.
 */
public class BenefitResult {

    private double rewardPoints;
    private String benefitPayMethod;

    public BenefitResult(double rewardPoints, String benefitPayMethod) {
        this.rewardPoints = rewardPoints;
        this.benefitPayMethod = benefitPayMethod;
    }

    public double getRewardPoints() {
        return rewardPoints;
    }

    public String getBenefitPayMethod() {
        return benefitPayMethod;
    }
}
