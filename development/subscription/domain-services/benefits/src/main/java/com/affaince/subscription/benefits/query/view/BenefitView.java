package com.affaince.subscription.benefits.query.view;

import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by rbsavaliya on 25-10-2015.
 */
@Document(collection = "BenefitView")
public class BenefitView {
    @Id
    private String benefitId;
    private String benefitEquation;
    private String benefitEquationInJsonFormat;
    private LocalDateTime activationStartTime;
    private LocalDateTime activationEndTime;

    public BenefitView(String benefitId, String benefitEquation, String benefitEquationInJsonFormat, LocalDateTime activationStartTime, LocalDateTime activationEndTime) {
        this.benefitId = benefitId;
        this.benefitEquation = benefitEquation;
        this.benefitEquationInJsonFormat = benefitEquationInJsonFormat;
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

    public String getBenefitEquationInJsonFormat() {
        return benefitEquationInJsonFormat;
    }

    public void setBenefitEquationInJsonFormat(String benefitEquationInJsonFormat) {
        this.benefitEquationInJsonFormat = benefitEquationInJsonFormat;
    }

    public LocalDateTime getActivationStartTime() {
        return activationStartTime;
    }

    public void setActivationStartTime(LocalDateTime activationStartTime) {
        this.activationStartTime = activationStartTime;
    }

    public LocalDateTime getActivationEndTime() {
        return activationEndTime;
    }

    public void setActivationEndTime(LocalDateTime activationEndTime) {
        this.activationEndTime = activationEndTime;
    }
}
