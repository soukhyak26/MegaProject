package com.affaince.subscription.payments.command.event;

import com.affaince.subscription.payments.vo.ModifiedSubscriptionContent;
import org.joda.time.LocalDate;

/**
 * Created by mandar on 6/4/2017.
 */
public class DeliveriesUpdatedWithCorrectedPaymentEvent {
    private String subscriberId;
    private String subscriptionId;
    private ModifiedSubscriptionContent modifiedSubscriptionContent;
    private LocalDate changeDate;

    public DeliveriesUpdatedWithCorrectedPaymentEvent(String subscriberId,String subscriptionId, ModifiedSubscriptionContent modifiedSubscriptionContent, LocalDate changeDate) {
        this.subscriberId = subscriberId;
        this.subscriptionId=subscriptionId;
        this.modifiedSubscriptionContent = modifiedSubscriptionContent;
        this.changeDate=changeDate;
    }

    public String getSubscriberId() {
        return subscriberId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public ModifiedSubscriptionContent getModifiedSubscriptionContent() {
        return modifiedSubscriptionContent;
    }

    public LocalDate getChangeDate() {
        return changeDate;
    }
}
