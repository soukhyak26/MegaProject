package com.affaince.subscription.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rule implements RuleSetPojo {
    private LogicalExpression condition;

    private Conclusion conclusion;

    private MoneyConversion moneyConversion;

    private PeriodConversion periodConversion;

    private ArithmeticExpression arithmeticExpression;

    private Offer offer;

    public Rule() {
    }

    public Rule(LogicalExpression condition, Conclusion conclusion) {
        this.condition = condition;
        this.conclusion = conclusion;
    }

    @JsonProperty("eligibilityCondition")
    public LogicalExpression getCondition() {
        return condition;
    }

    public void setCondition(LogicalExpression condition) {
        this.condition = condition;
    }

    @JsonProperty("payableCriteria")
    public Conclusion getConclusion() {
        return conclusion;
    }

    public void setConclusion(Conclusion conclusion) {
        this.conclusion = conclusion;
    }

    @JsonProperty ("moneyConversion")
    public MoneyConversion getMoneyConversion() {
        return moneyConversion;
    }

    public void setMoneyConversion(MoneyConversion moneyConversion) {
        this.moneyConversion = moneyConversion;
    }

    @JsonProperty ("periodConversion")
    public PeriodConversion getPeriodConversion() {
        return periodConversion;
    }

    public void setPeriodConversion(PeriodConversion periodConversion) {
        this.periodConversion = periodConversion;
    }

    @JsonProperty ("totalPointCalcExpr")
    public ArithmeticExpression getArithmeticExpression() {
        return arithmeticExpression;
    }

    public void setArithmeticExpression(ArithmeticExpression arithmeticExpression) {
        this.arithmeticExpression = arithmeticExpression;
    }

    @JsonProperty ("Offer")
    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rule rule = (Rule) o;

        if (conclusion != null ? !conclusion.equals(rule.conclusion) : rule.conclusion != null) return false;
        if (condition != null ? !condition.equals(rule.condition) : rule.condition != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = condition != null ? condition.hashCode() : 0;
        result = 31 * result + (conclusion != null ? conclusion.hashCode() : 0);
        return result;
    }
}
