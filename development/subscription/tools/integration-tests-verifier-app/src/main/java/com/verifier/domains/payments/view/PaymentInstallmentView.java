package com.verifier.domains.payments.view;

import com.verifier.domains.payments.vo.InstalmentPaymentTracker;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.stream.Collectors;

@Document (collection = "PaymentInstallmentView")
public class PaymentInstallmentView {
    private String subscriberId;
    @Id
    private String subscriptionId;
    private List<InstalmentPaymentTracker> instalmentPaymentTrackers;

    public PaymentInstallmentView() {
    }

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
    public InstalmentPaymentTracker findPaymentTrackerByDeliverySequence(int sequenceId) {
        return instalmentPaymentTrackers.stream().filter(dwpt -> dwpt.isGivenDeliverySequenceManagedByTracker(sequenceId)).collect(Collectors.toList()).get(0);
    }

}
