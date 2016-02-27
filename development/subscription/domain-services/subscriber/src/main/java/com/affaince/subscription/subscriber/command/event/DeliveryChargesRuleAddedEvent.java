package com.affaince.subscription.subscriber.command.event;

import com.affaince.subscription.common.type.DeliveryChargesRuleType;
import com.affaince.subscription.subscriber.vo.RangeRule;

import java.util.List;

/**
 * Created by rsavaliya on 27/2/16.
 */
public class DeliveryChargesRuleAddedEvent {
    private DeliveryChargesRuleType ruleId;
    private List<RangeRule> deliveryChargesRules;

    public DeliveryChargesRuleAddedEvent(DeliveryChargesRuleType ruleId, List<RangeRule> deliveryChargesRules) {
        this.ruleId = ruleId;
        this.deliveryChargesRules = deliveryChargesRules;
    }

    public DeliveryChargesRuleType getRuleId() {
        return ruleId;
    }

    public List<RangeRule> getDeliveryChargesRules() {
        return deliveryChargesRules;
    }
}
