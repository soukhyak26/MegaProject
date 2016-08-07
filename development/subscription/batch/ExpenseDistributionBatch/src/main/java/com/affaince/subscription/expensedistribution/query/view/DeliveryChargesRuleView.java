package com.affaince.subscription.expensedistribution.query.view;

import com.affaince.subscription.common.type.DeliveryChargesRuleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.Currency;
import java.util.List;

/**
 * Created by rsavaliya on 21/1/16.
 */
@Document(collection = "DeliveryChargesRuleView")
public class DeliveryChargesRuleView {
    @Id
    private DeliveryChargesRuleType ruleId;
    private RangeRule[] rangeRules;
    @Autowired
    private Currency currency;

    public DeliveryChargesRuleType getRuleId() {
        return ruleId;
    }

    public void setRuleId(DeliveryChargesRuleType ruleId) {
        this.ruleId = ruleId;
    }

    public List<RangeRule> getRangeRules() {
        return Arrays.asList(rangeRules);
    }

    public void setRangeRules(RangeRule[] rangeRules) {
        this.rangeRules = rangeRules;
    }

    public Currency getCurrency() {
        return currency;
    }
}
