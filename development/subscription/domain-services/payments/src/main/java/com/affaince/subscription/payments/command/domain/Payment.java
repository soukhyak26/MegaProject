package com.affaince.subscription.payments.command.domain;

import com.affaince.subscription.payments.command.DeliveryCreatedCommand;
import com.affaince.subscription.payments.command.DeliveryStatusAndDispatchDateUpdatedCommand;
import com.affaince.subscription.payments.command.accounting.DeliveryCostAccount;
import com.affaince.subscription.payments.command.accounting.PaymentReceivedAccount;
import com.affaince.subscription.payments.command.event.DeliveryInitiatedEvent;
import com.affaince.subscription.payments.command.event.PaymentInitiatedEvent;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcedMember;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

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
    @EventSourcedMember
    private Map<String, DeliveryCostAccount> deliveryCostAccountMap;
    @EventSourcedMember
    private PaymentReceivedAccount paymentReceivedAccount;

    //TODO: What about subscriber id (i.e. same subscriber and multiple subscriptions) - will there be repository for that too?

    public Payment() {
        /*deliveryCosts = new HashMap<>();
        deliveryCostAccountMap = new HashMap<>();*/
    }

    public Payment(String subscriptionId, Double totalBalance) {
        /*this();
        this.subscriptionId = subscriptionId;
        paymentReceivedAccount = new PaymentReceivedAccount(totalBalance);*/
        apply(new PaymentInitiatedEvent(subscriptionId, totalBalance));
    }

    @EventSourcingHandler
    public void on(PaymentInitiatedEvent event) {
        this.subscriptionId = event.getSubscriptionId();
        this.totalBalance = event.getTotalBalance();
        this.deliveryCostAccountMap = new HashMap<>();
    }

    public void handlePartialPayment(Double partialPayment) {
        this.paymentReceivedAccount.fireCreditedEvent(this.subscriptionId, partialPayment);
    }

    public void handleDeliveryStatusAndDispatchDateUpdatedCommand(DeliveryStatusAndDispatchDateUpdatedCommand command) {
        /*double deliveryCost = command.getDeliveryCharges() + command.getTotalDeliveryPrice();
        totalBalance -= deliveryCost;
        deliveryCosts.put(command.getBasketId(), deliveryCost);*/
        this.deliveryCostAccountMap.get(command.getBasketId()).fireCreditedEvent(command.getBasketId(), command.getDeliveryCharges() + command.getTotalDeliveryPrice());
        this.paymentReceivedAccount.fireDebitedEvent(command.getSubscriptionId(), command.getDeliveryCharges() + command.getTotalDeliveryPrice());
    }

    /*public void handlePaymentReceivedCommand(PaymentReceivedCommand command) {
        totalBalance += command.getPaidAmount();
    }*/

    public void handleDeliveryCreatedCommand(DeliveryCreatedCommand command) {
        //deliveryCosts.put(command.getDeliveryId(), 0.0);
        apply(new DeliveryInitiatedEvent(command.getDeliveryId(), command.getSubscriptionId()));
    }

    @EventSourcingHandler
    public void on(DeliveryInitiatedEvent event) {
        this.deliveryCostAccountMap.put(event.getDeliveryId(), new DeliveryCostAccount(0));
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

}
