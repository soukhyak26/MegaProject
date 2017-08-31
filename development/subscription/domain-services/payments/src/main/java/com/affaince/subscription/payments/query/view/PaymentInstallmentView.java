package com.affaince.subscription.payments.query.view;

import com.affaince.subscription.payments.vo.InstalmentPaymentTracker;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document (collection = "PaymentInstallmentView")
public class PaymentInstallmentView {
    private String subscriberId;
    @Id
    private String subscriptionId;
    private List<InstalmentPaymentTracker> instalmentPaymentTrackers;

    public PaymentInstallmentView(String subscriberId, String subscriptionId, List<InstalmentPaymentTracker> instalmentPaymentTrackers) {
        this.subscriberId = subscriberId;
        this.subscriptionId = subscriptionId;
        this.instalmentPaymentTrackers = instalmentPaymentTrackers;
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
