package com.affaince.payments.dummy;

import java.util.List;

public class DummyEvent3 {
    private int subscriptionRenewalCount;
    private List<Integer> subscriptionPeriod;
    private int totalDeliveries;

    public DummyEvent3(int subscriptionRenewalCount, List<Integer> subscriptionPeriod, int totalDeliveries) {
        this.subscriptionRenewalCount = subscriptionRenewalCount;
        this.subscriptionPeriod = subscriptionPeriod;
        this.totalDeliveries = totalDeliveries;
    }

    public int getSubscriptionRenewalCount() {
        return subscriptionRenewalCount;
    }

    public List<Integer> getSubscriptionPeriod() {
        return subscriptionPeriod;
    }

    public int getTotalDeliveries() {
        return totalDeliveries;
    }
}
