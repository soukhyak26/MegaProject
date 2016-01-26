package com.affaince.subscription.business.web.request;


import com.affaince.subscription.business.vo.RangeRule;

import java.util.List;

/**
 * Created by rbsavaliya on 16-01-2016.
 */
public class DeliveryChargesRulesRequest {
    private List<RangeRule> deliveryChargesRules;

    public List<RangeRule> getDeliveryChargesRules() {
        return deliveryChargesRules;
    }

    public void setDeliveryChargesRules(List<RangeRule> deliveryChargesRules) {
        this.deliveryChargesRules = deliveryChargesRules;
    }
}
