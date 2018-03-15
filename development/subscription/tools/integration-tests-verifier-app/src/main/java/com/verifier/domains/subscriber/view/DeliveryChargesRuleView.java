package com.verifier.domains.subscriber.view;

import com.affaince.subscription.common.type.DeliveryChargesRuleType;
import com.verifier.domains.subscriber.vo.RangeRule;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Currency;
import java.util.List;

/**
 * Created by rsavaliya on 21/1/16.
 */
@Document(collection = "DeliveryChargesRuleView")
public class DeliveryChargesRuleView {
    @Id
    private DeliveryChargesRuleType ruleId;
    private List <RangeRule> rangeRules;
    @Autowired
    private Currency currency;
    private LocalDate effectiveDate;


    public DeliveryChargesRuleView(DeliveryChargesRuleType ruleId, List<RangeRule> rangeRules, LocalDate effectiveDate) {
        this.ruleId = ruleId;
        this.rangeRules = rangeRules;
        this.effectiveDate = effectiveDate;
    }

    public DeliveryChargesRuleType getRuleId() {
        return ruleId;
    }

    public void setRuleId(DeliveryChargesRuleType ruleId) {
        this.ruleId = ruleId;
    }

    public List<RangeRule> getRangeRules() {
        return rangeRules;
    }

    public void setRangeRules(List<RangeRule> rangeRules) {
        this.rangeRules = rangeRules;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
