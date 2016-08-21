package com.affaince.subscription.payments.command.domain;

import com.affaince.subscription.payments.command.DeliveryCreatedCommand;
import com.affaince.subscription.payments.command.DeliveryStatusAndDispatchDateUpdatedCommand;
import com.affaince.subscription.payments.command.PaymentReceivedCommand;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anayonkar on 21/8/16.
 */
public class Payment extends AbstractAnnotatedAggregateRoot<String> {

    @AggregateIdentifier
    private String subscriptionId;
    //TODO: change below 2 fields to abstractannotatedentity and mark as eventsourcedmember
    //TODO: create 2 views - 1 for delivery(i.e. debit) and another for payment received(i.e. credit)
    private Map<String, Double> deliveryCosts;
    private Double totalBalance;

    public Payment() {
        deliveryCosts = new HashMap<>();
    }

    public Payment(String subscriptionId) {
        this();
        this.subscriptionId = subscriptionId;
    }

    public void handleDeliveryStatusAndDispatchDateUpdatedCommand(DeliveryStatusAndDispatchDateUpdatedCommand command) {
        double deliveryCost = command.getDeliveryCharges() + command.getTotalDeliveryPrice();
        totalBalance -= deliveryCost;
        deliveryCosts.put(command.getBasketId(), deliveryCost);
    }

    public void handlePaymentReceivedCommand(PaymentReceivedCommand command) {
        totalBalance += command.getPaidAmount();
    }

    public void handleDeliveryCreatedCommand(DeliveryCreatedCommand command) {
        deliveryCosts.put(command.getDeliveryId(), 0.0);
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

}
