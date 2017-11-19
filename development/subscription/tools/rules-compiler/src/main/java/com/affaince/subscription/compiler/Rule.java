package com.affaince.subscription.compiler;

import com.affaince.subscription.pojos.BenefitDistributionParameters;
import com.affaince.subscription.pojos.EligibilityParameter;

import java.util.List;

public class Rule {

    private String eligibilityCondition ;

    private PointConversionParameters pointConversionParameters;

    private String pointConversionExpression;

    private String benefitPaymentMethod;

    private BenefitDistributionParameters benefitDistributionParameters;

    private List<EligibilityParameter> eligibilityParameters;

    public Rule() {
    }

    public String getEligibilityCondition() {
        return eligibilityCondition;
    }

    public void setEligibilityCondition(String eligibilityCondition) {
        this.eligibilityCondition = eligibilityCondition;
    }

    public PointConversionParameters getPointConversionParameters() {
        return pointConversionParameters;
    }

    public void setPointConversionParameters(PointConversionParameters pointConversionParameters) {
        this.pointConversionParameters = pointConversionParameters;
    }

    public String getPointConversionExpression() {
        return pointConversionExpression;
    }

    public void setPointConversionExpression(String pointConversionExpression) {
        this.pointConversionExpression = pointConversionExpression;
    }

    public String getBenefitPaymentMethod() {
        return benefitPaymentMethod;
    }

    public void setBenefitPaymentMethod(String benefitPaymentMethod) {
        this.benefitPaymentMethod = benefitPaymentMethod;
    }

    public BenefitDistributionParameters getBenefitDistributionParameters() {
        return benefitDistributionParameters;
    }

    public void setBenefitDistributionParameters(BenefitDistributionParameters benefitDistributionParameters) {
        this.benefitDistributionParameters = benefitDistributionParameters;
    }

    public List<EligibilityParameter> getEligibilityParameters() {
        return eligibilityParameters;
    }

    public void setEligibilityParameters(List<EligibilityParameter> eligibilityParameters) {
        this.eligibilityParameters = eligibilityParameters;
    }
}
