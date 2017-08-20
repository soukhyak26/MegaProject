package com.affaince.subscription.benefit.simulator.request;

/**
 * Created by rbsavaliya on 25-10-2015.
 */
public class Benefit {

    private String benefitEquation;
    private String activationStartTime;
    private String activationEndTime;

    public String getBenefitEquation() {
        return benefitEquation;
    }

    public void setBenefitEquation(String benefitEquation) {
        this.benefitEquation = benefitEquation;
    }

    public String getActivationStartTime() {
        return activationStartTime;
    }

    public void setActivationStartTime(String activationStartTime) {
        this.activationStartTime = activationStartTime;
    }

    public String getActivationEndTime() {
        return activationEndTime;
    }

    public void setActivationEndTime(String activationEndTime) {
        this.activationEndTime = activationEndTime;
    }
}
