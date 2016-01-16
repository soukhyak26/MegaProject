package com.affaince.subscription.subscriber.command;

import com.affaince.subscription.subscriber.vo.RangeRule;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

import java.util.List;

/**
 * Created by rbsavaliya on 16-01-2016.
 */
public class SetDeliveryChargesRulesCommand {
    @TargetAggregateIdentifier
    private final String id;
    private final String expenseHeader;
    private final List<RangeRule> deliveryChargesRules;

    public SetDeliveryChargesRulesCommand(String id, String expenseHeader, List<RangeRule> deliveryChargesRules) {
        this.id = id;
        this.expenseHeader = expenseHeader;
        this.deliveryChargesRules = deliveryChargesRules;
    }

    public String getId() {
        return id;
    }

    public List<RangeRule> getDeliveryChargesRules() {
        return deliveryChargesRules;
    }

    public String getExpenseHeader() {
        return expenseHeader;
    }
}
