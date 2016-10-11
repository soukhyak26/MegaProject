package com.affaince.subscription.subscriber.command.domain;

import com.affaince.subscription.common.type.DeliveryChargesRuleType;
import com.affaince.subscription.subscriber.vo.RangeRule;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by rsavaliya on 27/2/16.
 */
public class DeliveryChargesRule {
    private DeliveryChargesRuleType ruleId;
    private List<RangeRule> deliveryChargesRules;
    private LocalDate effectiveDate;

    public DeliveryChargesRule(DeliveryChargesRuleType ruleId, List<RangeRule> deliveryChargesRules, LocalDate effectiveDate) {
        this.ruleId = ruleId;
        this.deliveryChargesRules = deliveryChargesRules;
        this.effectiveDate = effectiveDate;
    }

    public DeliveryChargesRuleType getRuleId() {
        return ruleId;
    }

    public List<RangeRule> getDeliveryChargesRules() {
        return deliveryChargesRules;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }
}
