package com.affaince.subscription.payments.query.view;

import com.affaince.subscription.payments.vo.PaymentTransactionType;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 5/18/2017.
 */
@Document(collection ="PaymentTransactionView")
public class PaymentTransactionView {
    @Id
    private String transactionId;
    private LocalDate transactionDate;
    private String subscriptionId;
    private double transactionAmount;
    private PaymentTransactionType paymentTransactionType;

    public PaymentTransactionView(String transactionId, LocalDate transactionDate, String subscriptionId, double transactionAmount, PaymentTransactionType paymentTransactionType) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.subscriptionId = subscriptionId;
        this.transactionAmount = transactionAmount;
        this.paymentTransactionType = paymentTransactionType;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public PaymentTransactionType getPaymentTransactionType() {
        return paymentTransactionType;
    }
}
