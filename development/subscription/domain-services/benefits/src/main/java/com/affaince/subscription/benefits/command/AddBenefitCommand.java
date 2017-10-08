package com.affaince.subscription.benefits.command;


import org.axonframework.commandhandling.TargetAggregateIdentifier;

/**
 * Created by rbsavaliya on 25-10-2015.
 */
public class AddBenefitCommand {

    @TargetAggregateIdentifier
    private String benefitId;
    private String benefitEquation;
    private String activationStartTime;
    private String activationEndTime;

    public AddBenefitCommand(String benefitId, String benefitEquation, String activationStartTime, String activationEndTime) {
        this.benefitId = benefitId;
        this.benefitEquation = benefitEquation;
        this.activationStartTime = activationStartTime;
        this.activationEndTime = activationEndTime;
    }

    public String getBenefitId() {

        return benefitId;
    }

    public void setBenefitId(String benefitId) {
        this.benefitId = benefitId;
    }

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
