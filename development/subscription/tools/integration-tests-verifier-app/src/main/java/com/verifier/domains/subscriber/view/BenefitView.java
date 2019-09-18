package com.verifier.domains.subscriber.view;

import com.affaince.subscription.common.deserializer.LocalDateDeserializer;
import com.affaince.subscription.common.serializer.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by rbsavaliya on 25-10-2015.
 */
@Document(collection = "BenefitView")
public class BenefitView {
    @Id
    private String benefitId;
    private String benefitEquation;
    private String benefitEquationInJsonFormat;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate activationStartTime;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate activationEndTime;
    private List<String> canApplicableWith;

    public BenefitView() {
    }

    public BenefitView(String benefitId, String benefitEquation, String benefitEquationInJsonFormat, LocalDate activationStartTime, LocalDate activationEndTime, List<String> canApplicableWith) {
        this.benefitId = benefitId;
        this.benefitEquation = benefitEquation;
        this.benefitEquationInJsonFormat = benefitEquationInJsonFormat;
        this.activationStartTime = activationStartTime;
        this.activationEndTime = activationEndTime;
        this.canApplicableWith = canApplicableWith;
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

    public List<String> getCanApplicableWith() {
        return canApplicableWith;
    }

    public void setCanApplicableWith(List<String> canApplicableWith) {
        this.canApplicableWith = canApplicableWith;
    }
}
