package com.affaince.subscription.payments.query.view;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 7/17/2017.
 */
@Document(collection = "RefundView")
public class RefundView {
    @Id
    private String subscriptionId;
    private double refundAmount;

    public RefundView(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public double getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(double refundAmount) {
        this.refundAmount = refundAmount;
    }
}
