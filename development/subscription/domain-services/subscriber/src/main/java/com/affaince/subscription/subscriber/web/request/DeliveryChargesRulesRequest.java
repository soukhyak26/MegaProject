package com.affaince.subscription.subscriber.web.request;


import com.affaince.subscription.subscriber.vo.RangeRule;

import java.util.List;

/**
 * Created by rbsavaliya on 16-01-2016.
 */
public class DeliveryChargesRulesRequest {
    private int ruleId;
    private List<RangeRule> deliveryChargesRules;

    public List<RangeRule> getDeliveryChargesRules() {
        return deliveryChargesRules;
    }

    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    public void setDeliveryChargesRules(List<RangeRule> deliveryChargesRules) {
        this.deliveryChargesRules = deliveryChargesRules;
    }
}
