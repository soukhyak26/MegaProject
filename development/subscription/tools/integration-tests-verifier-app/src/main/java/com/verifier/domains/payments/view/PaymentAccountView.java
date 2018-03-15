package com.verifier.domains.payments.view;

import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 7/9/2017.
 */
@Document(collection="PaymentAccountView")
public class PaymentAccountView {
    @Id
    private String subscriptionId;
    private String subscriberId;
    private String paymentSchemeId;
    private LocalDate creationDate;

    public PaymentAccountView(String subscriptionId, String subscriberId,LocalDate creationDate) {
        this.subscriptionId = subscriptionId;
        this.subscriberId = subscriberId;
        this.creationDate = creationDate;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public String getPaymentSchemeId() {
        return paymentSchemeId;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setPaymentSchemeId(String paymentSchemeId) {
        this.paymentSchemeId = paymentSchemeId;
    }
}
