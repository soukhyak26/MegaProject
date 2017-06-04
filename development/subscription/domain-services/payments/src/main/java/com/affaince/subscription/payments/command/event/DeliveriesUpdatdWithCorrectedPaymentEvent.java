package com.affaince.subscription.payments.command.event;

import com.affaince.subscription.payments.vo.ModifiedSubscriptionContent;
import org.joda.time.LocalDate;

/**
 * Created by mandar on 6/4/2017.
 */
public class DeliveriesUpdatdWithCorrectedPaymentEvent {
    private String subscriberId;
    private ModifiedSubscriptionContent modifiedSubscriptionContent;
    private LocalDate changeDate;

    public DeliveriesUpdatdWithCorrectedPaymentEvent(String subscriberId, ModifiedSubscriptionContent modifiedSubscriptionContent,LocalDate changeDate) {
        this.subscriberId = subscriberId;
        this.modifiedSubscriptionContent = modifiedSubscriptionContent;
        this.changeDate=changeDate;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public ModifiedSubscriptionContent getModifiedSubscriptionContent() {
        return modifiedSubscriptionContent;
    }

    public LocalDate getChangeDate() {
        return changeDate;
    }
}
