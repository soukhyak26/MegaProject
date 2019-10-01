package com.verifier.domains.payments.view;

import com.affaince.subscription.common.deserializer.LocalDateDeserializer;
import com.affaince.subscription.common.serializer.LocalDateSerializer;import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.verifier.domains.payments.vo.PaymentTransactionType;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.LocalTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 5/18/2017.
 */
@Document(collection ="PaymentTransactionView")
public class PaymentTransactionView {
    @Id
    private long transactionId;
   @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate transactionDate;
    private String subscriptionId;
    private double transactionAmount;
    private PaymentTransactionType paymentTransactionType;

    public PaymentTransactionView(LocalDate transactionDate, String subscriptionId, double transactionAmount, PaymentTransactionType paymentTransactionType) {
        final LocalDateTime currentDateTime=transactionDate.toLocalDateTime(LocalTime.now());
        this.transactionId = currentDateTime.toDateTime().getMillis();;
        this.transactionDate = transactionDate;
        this.subscriptionId = subscriptionId;
        this.transactionAmount = transactionAmount;
        this.paymentTransactionType = paymentTransactionType;
    }

    public long getTransactionId() {
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
