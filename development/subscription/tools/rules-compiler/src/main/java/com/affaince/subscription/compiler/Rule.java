package com.affaince.subscription.compiler;

public class Rule {

    private String eligibilityCondition ;

    private Conclusion applicability;

    private MoneyConversion moneyConversion;

    private PeriodConversion periodConversion;

    private String pointConversionExpression;

    private Offer offer;

    public Rule() {
    }

    public String getEligibilityCondition() {
        return eligibilityCondition;
    }

    public void setEligibilityCondition(String eligibilityCondition) {
        this.eligibilityCondition = eligibilityCondition;
    }

    public Conclusion getApplicability() {
        return applicability;
    }

    public void setApplicability(Conclusion applicability) {
        this.applicability = applicability;
    }

    public MoneyConversion getMoneyConversion() {
        return moneyConversion;
    }

    public void setMoneyConversion(MoneyConversion moneyConversion) {
        this.moneyConversion = moneyConversion;
    }

    public PeriodConversion getPeriodConversion() {
        return periodConversion;
    }

    public void setPeriodConversion(PeriodConversion periodConversion) {
        this.periodConversion = periodConversion;
    }

    public String getPointConversionExpression() {
        return pointConversionExpression;
    }

    public void setPointConversionExpression(String pointConversionExpression) {
        this.pointConversionExpression = pointConversionExpression;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }
}
