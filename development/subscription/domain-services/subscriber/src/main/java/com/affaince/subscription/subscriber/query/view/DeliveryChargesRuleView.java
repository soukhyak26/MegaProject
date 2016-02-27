package com.affaince.subscription.subscriber.query.view;

import com.affaince.subscription.common.type.QuantityUnit;
import com.affaince.subscription.subscriber.vo.RangeRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Currency;
import java.util.List;

/**
 * Created by rsavaliya on 21/1/16.
 */
@Document(collection = "DeliveryChargesRuleView")
public class DeliveryChargesRuleView {
    @Id
    private int ruleId;
    private List <RangeRule> rangeRules;
    @Autowired
    private Currency currency;

    public DeliveryChargesRuleView(int ruleId, List<RangeRule> rangeRules) {
        this.ruleId = ruleId;
        this.rangeRules = rangeRules;
    }

    public int getRuleId() {
        return ruleId;
    }

    public void setRuleId(int ruleId) {
        this.ruleId = ruleId;
    }

    public List<RangeRule> getRangeRules() {
        return rangeRules;
    }

    public void setRangeRules(List<RangeRule> rangeRules) {
        this.rangeRules = rangeRules;
    }

    public Currency getCurrency() {
        return currency;
    }
}
