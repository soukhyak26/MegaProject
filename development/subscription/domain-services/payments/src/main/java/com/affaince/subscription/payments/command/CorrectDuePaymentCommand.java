package com.affaince.subscription.payments.command;

import com.affaince.subscription.common.type.DeliveryStatus;
import org.joda.time.LocalDate;


public class CorrectDuePaymentCommand {
    private String subscriptionId;
    private LocalDate dueCorrectionDate;
    public CorrectDuePaymentCommand(String subscriptionId,LocalDate dueCorrectionDate) {
        this.subscriptionId = subscriptionId;
        this.dueCorrectionDate=dueCorrectionDate;
    }

    public CorrectDuePaymentCommand() {
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public LocalDate getDueCorrectionDate() {
        return dueCorrectionDate;
    }
}
