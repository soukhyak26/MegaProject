package com.affaince.subscription.benefits.command.event;

import org.joda.time.LocalDate;

/**
 * Created by rbsavaliya on 25-10-2015.
 */
public class BenefitAddedEvent {

    private String benefitId;
    private String benefitEquation;
    private String benefitEquationInJsonFormat;
    private LocalDate activationStartTime;
    private LocalDate activationEndTime;

    public BenefitAddedEvent(String benefitId, String benefitEquation, String benefitEquationInJsonFormat, LocalDate activationStartTime, LocalDate activationEndTime) {
        this.benefitId = benefitId;
        this.benefitEquation = benefitEquation;
        this.benefitEquationInJsonFormat = benefitEquationInJsonFormat;
        this.activationStartTime = activationStartTime;
        this.activationEndTime = activationEndTime;
    }

    public BenefitAddedEvent() {
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

    public String getBenefitEquationInJsonFormat() {
        return benefitEquationInJsonFormat;
    }

    public void setBenefitEquationInJsonFormat(String benefitEquationInJsonFormat) {
        this.benefitEquationInJsonFormat = benefitEquationInJsonFormat;
    }

    public LocalDate getActivationStartTime() {
        return activationStartTime;
    }

    public void setActivationStartTime(LocalDate activationStartTime) {
        this.activationStartTime = activationStartTime;
    }

    public LocalDate getActivationEndTime() {
        return activationEndTime;
    }

    public void setActivationEndTime(LocalDate activationEndTime) {
        this.activationEndTime = activationEndTime;
    }
}
