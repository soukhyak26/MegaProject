package com.affaince.subscription.compiler;

import com.affaince.subscription.pojos.RuleSetPojo;
import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonSerialize(using= ConclusionSerializer.class)
public class Conclusion implements RuleSetPojo {
    private short advancePaymentPercentage;
    private String benefitPayMethod;
    private String periodOption;
    private String benefitPaymentFrequency;

    @JsonProperty ("AdvancePaymentPercentage")
    public short getAdvancePaymentPercentage() {
        return advancePaymentPercentage;
    }

    public void setAdvancePaymentPercentage(short advancePaymentPercentage) {
        this.advancePaymentPercentage = advancePaymentPercentage;
    }

    @JsonProperty ("BenefitPayMethod")
    public String getBenefitPayMethod() {
        return benefitPayMethod;
    }

    public void setBenefitPayMethod(String benefitPayMethod) {
        this.benefitPayMethod = benefitPayMethod;
    }

    @JsonProperty ("PeriodOption")
    public String getPeriodOption() {
        return periodOption;
    }

    public void setPeriodOption(String periodOption) {
        this.periodOption = periodOption;
    }

    @JsonProperty ("BenefitPaymentFrequency")
    public String getBenefitPaymentFrequency() {
        return benefitPaymentFrequency;
    }

    public void setBenefitPaymentFrequency(String benefitPaymentFrequency) {
        this.benefitPaymentFrequency = benefitPaymentFrequency;
    }
}
