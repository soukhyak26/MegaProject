package com.affaince.subscription.payments.event;

import com.affaince.subscription.payments.vo.InstalmentPaymentTracker;

import java.util.List;

public class PaymentInstallmentCalculatedEvent {
    private String subscriberId;
    private String subscriptionId;
    private List<InstalmentPaymentTracker> instalmentPaymentTrackers;

    public PaymentInstallmentCalculatedEvent(String subscriberId, String subscriptionId, List<InstalmentPaymentTracker> instalmentPaymentTrackers) {
        this.subscriberId = subscriberId;
        this.subscriptionId = subscriptionId;
        this.instalmentPaymentTrackers = instalmentPaymentTrackers;
    }

    public PaymentInstallmentCalculatedEvent() {
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public List<InstalmentPaymentTracker> getInstalmentPaymentTrackers() {
        return instalmentPaymentTrackers;
    }
}
