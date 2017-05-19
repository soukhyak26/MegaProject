package com.affaince.subscription.payments.command.accounting;

import com.affaince.subscription.payments.command.event.DeliveryCostAccountCreditedEvent;
import com.affaince.subscription.payments.command.event.DeliveryCostAccountDebitedEvent;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 23/8/16.
 */
public class DeliveryCostAccount extends Account {
    private String deliveryId;
    private String subscriptionId;

    public DeliveryCostAccount(String deliveryId, String subscriptionId, LocalDate deliveryDate, double totalDeliveryCost) {
        super(totalDeliveryCost,deliveryDate);
        this.deliveryId = deliveryId;
        this.subscriptionId = subscriptionId;
    }
}
