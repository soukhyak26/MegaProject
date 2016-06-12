package com.affaince.subscription.compiler;

public class Rule {

    private String eligibilityCondition ;

    private PointConversionParameters pointConversionParameters;

    private String pointConversionExpression;

    private String benefitPaymentMethod;

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
}
