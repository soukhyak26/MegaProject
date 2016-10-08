package com.affaince.subscription.testdata.generator.com.affaince.subscription.benefits.command.event;

import java.io.Serializable;

/**
 * Created by rbsavaliya on 25-10-2015.
 */
public class BenefitAddedEvent implements Serializable {

    private String benefitId;
    private String benefitEquation;
    private String benefitEquationInJsonFormat;

    public BenefitAddedEvent(String benefitId, String benefitEquation, String benefitEquationInJsonFormat) {
        this.benefitId = benefitId;
        this.benefitEquation = benefitEquation;
        this.benefitEquationInJsonFormat = benefitEquationInJsonFormat;
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
}
