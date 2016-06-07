package com.affaince.subscription.subscriber.services;

/**
 * Created by rbsavaliya on 29-05-2016.
 */
public class BenefitResult {

    private double rewardPoints;
    private String paymentType;
    private String periodOption;
    private String benefitPaymentFrequency;

    public BenefitResult(double rewardPoints, String paymentType, String periodOption, String benefitPaymentFrequency) {
        this.rewardPoints = rewardPoints;
        this.paymentType = paymentType;
        this.periodOption = periodOption;
        this.benefitPaymentFrequency = benefitPaymentFrequency;
    }

    public double getRewardPoints() {
        return rewardPoints;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public String getPeriodOption() {
        return periodOption;
    }

    public String getBenefitPaymentFrequency() {
        return benefitPaymentFrequency;
    }
}
