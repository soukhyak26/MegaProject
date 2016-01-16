package com.affaince.subscription.product.registration.command.event;


import com.affaince.subscription.product.registration.vo.RangeRule;

import java.util.List;

/**
 * Created by rbsavaliya on 16-01-2016.
 */
public class SubscriptionSpecificExpenseAddedEvent {
    private final String id;
    private final String expenseHeader;
    private final List<RangeRule> deliveryChargesRules;

    public SubscriptionSpecificExpenseAddedEvent(String id, String expenseHeader, List<RangeRule> deliveryChargesRules) {
        this.id = id;
        this.expenseHeader = expenseHeader;
        this.deliveryChargesRules = deliveryChargesRules;
    }

    public String getId() {
        return id;
    }

    public String getExpenseHeader() {
        return expenseHeader;
    }

    public List<RangeRule> getDeliveryChargesRules() {
        return deliveryChargesRules;
    }
}
