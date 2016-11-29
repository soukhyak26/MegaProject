package com.affaince.subscription.delivery.query.view;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by rbsavaliya on 27-09-2015.
 */
@Document(collection = "SubscriptionRule")
public class SubscriptionRuleView {
    @Id
    private String basketRuleId;
    private int diffBetweenDeliveryPreparationAndDispatchDate;


    public SubscriptionRuleView(String basketRuleId, int diffBetweenDeliveryPreparationAndDispatchDate) {
        this.basketRuleId = basketRuleId;
        this.diffBetweenDeliveryPreparationAndDispatchDate = diffBetweenDeliveryPreparationAndDispatchDate;
    }

    public String getBasketRuleId() {
        return basketRuleId;
    }

    public void setBasketRuleId(String basketRuleId) {
        this.basketRuleId = basketRuleId;
    }

    public int getDiffBetweenDeliveryPreparationAndDispatchDate() {
        return diffBetweenDeliveryPreparationAndDispatchDate;
    }

    public void setDiffBetweenDeliveryPreparationAndDispatchDate(int diffBetweenDeliveryPreparationAndDispatchDate) {
        this.diffBetweenDeliveryPreparationAndDispatchDate = diffBetweenDeliveryPreparationAndDispatchDate;
    }
}
