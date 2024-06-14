package com.affaince.payments.dummy;

import java.util.List;

public class DummyEvent4 {
    private List<Integer> productSubscriptionCountPerSubscription;
    private String productId;
    private int totalDeliveries;

    public DummyEvent4(List<Integer> productSubscriptionCountPerSubscription, String productId,int totalDeliveries) {
        this.productSubscriptionCountPerSubscription = productSubscriptionCountPerSubscription;
        this.productId = productId;
        this.totalDeliveries=totalDeliveries;
    }

    public List<Integer> getProductSubscriptionCountPerSubscription() {
        return productSubscriptionCountPerSubscription;
    }

    public String getProductId() {
        return productId;
    }

    public int getTotalDeliveries() {
        return totalDeliveries;
    }
}
