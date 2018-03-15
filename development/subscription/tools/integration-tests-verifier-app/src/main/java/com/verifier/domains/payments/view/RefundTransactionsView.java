package com.verifier.domains.payments.view;

import com.verifier.domains.payments.vo.RefundProcessingStatus;
import com.verifier.domains.payments.vo.RefundReason;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * Created by mandar on 7/13/2017.
 */
@Document(collection = "RefundTransactionsView")
public class RefundTransactionsView {
    @Id
    private String subscriptionId;
    private String deliveryId;
    private Map<String,Double> itemWiseRefundAmountDetails;
    private RefundReason reasonForRefund;
    private double totalRefundAmount;
    private LocalDate refundDate;
    private RefundProcessingStatus refundProcessingStatus;

    public RefundTransactionsView(String subscriptionId) {
        this.subscriptionId = subscriptionId;
        this.refundProcessingStatus=RefundProcessingStatus.PENDING;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public Map<String, Double> getItemWiseRefundAmountDetails() {
        return itemWiseRefundAmountDetails;
    }

    public void setItemWiseRefundAmountDetails(Map<String, Double> itemWiseRefundAmountDetails) {
        this.itemWiseRefundAmountDetails = itemWiseRefundAmountDetails;
    }

    public RefundReason getReasonForRefund() {
        return reasonForRefund;
    }

    public void setReasonForRefund(RefundReason reasonForRefund) {
        this.reasonForRefund = reasonForRefund;
    }

    public double getTotalRefundAmount() {
        return totalRefundAmount;
    }

    public void setTotalRefundAmount(double totalRefundAmount) {
        this.totalRefundAmount = totalRefundAmount;
    }

    public LocalDate getRefundDate() {
        return refundDate;
    }

    public void setRefundDate(LocalDate refundDate) {
        this.refundDate = refundDate;
    }

    public RefundProcessingStatus getRefundProcessingStatus() {
        return refundProcessingStatus;
    }

    public void setRefundProcessingStatus(RefundProcessingStatus refundProcessingStatus) {
        this.refundProcessingStatus = refundProcessingStatus;
    }
}
