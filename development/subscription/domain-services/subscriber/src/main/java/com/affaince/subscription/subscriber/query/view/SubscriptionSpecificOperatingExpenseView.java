package com.affaince.subscription.subscriber.query.view;

import com.affaince.subscription.subscriber.vo.RangeRule;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by rbsavaliya on 16-01-2016.
 */
@Document(collection = "SubscriptionSpecificOperatingExpenseView")
public class SubscriptionSpecificOperatingExpenseView {
    @Id
    private String id;
    private String expenseHeader;
    private List<RangeRule> deliveryChargesRules;

    public SubscriptionSpecificOperatingExpenseView(String id, String expenseHeader, List<RangeRule> deliveryChargesRules) {
        this.id = id;
        this.expenseHeader = expenseHeader;
        this.deliveryChargesRules = deliveryChargesRules;
    }

    public SubscriptionSpecificOperatingExpenseView() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExpenseHeader() {
        return expenseHeader;
    }

    public void setExpenseHeader(String expenseHeader) {
        this.expenseHeader = expenseHeader;
    }

    public List<RangeRule> getDeliveryChargesRules() {
        return deliveryChargesRules;
    }

    public void setDeliveryChargesRules(List<RangeRule> deliveryChargesRules) {
        this.deliveryChargesRules = deliveryChargesRules;
    }
}
