package com.affaince.subscription.subscriber.command;

import com.affaince.subscription.subscriber.vo.RangeRule;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.List;

/**
 * Created by rsavaliya on 27/2/16.
 */
public class AddDeliveryChargesRuleCommand {
    @TargetAggregateIdentifier
    private int ruleId;
    private List<RangeRule> deliveryChargesRules;

    public AddDeliveryChargesRuleCommand(int ruleId, List<RangeRule> deliveryChargesRules) {
        this.ruleId = ruleId;
        this.deliveryChargesRules = deliveryChargesRules;
    }

    public int getRuleId() {
        return ruleId;
    }

    public List<RangeRule> getDeliveryChargesRules() {
        return deliveryChargesRules;
    }
}
