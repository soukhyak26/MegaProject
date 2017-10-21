package com.affaince.subscription.subscriber.web.request;


import com.affaince.subscription.subscriber.vo.RangeRule;

import java.util.Currency;

/**
 * Created by rbsavaliya on 16-01-2016.
 */
public class DeliveryChargesRulesRequest {
    private int ruleId;
    private RangeRule[] deliveryChargesRules;
    private Currency currency;

    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    public RangeRule[] getDeliveryChargesRules() {
        return deliveryChargesRules;
    }

    public void setDeliveryChargesRules(RangeRule[] deliveryChargesRules) {
        this.deliveryChargesRules = deliveryChargesRules;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}
