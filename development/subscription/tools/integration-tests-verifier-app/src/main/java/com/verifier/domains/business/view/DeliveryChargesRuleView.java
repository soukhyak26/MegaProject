package com.verifier.domains.business.view;

import com.affaince.subscription.common.type.DeliveryChargesRuleType;
import com.affaince.subscription.common.vo.RangeRule;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection = "DeliveryChargesRuleView")
public class DeliveryChargesRuleView {
    @Id
    private DeliveryChargesRuleType ruleId;
    private List<RangeRule> rangeRules;
    private LocalDate effectiveDate;


    public DeliveryChargesRuleView(){}
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

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
