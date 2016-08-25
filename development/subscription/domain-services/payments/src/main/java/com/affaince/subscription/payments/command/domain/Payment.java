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
    @EventSourcedMember
    private Map<String, DeliveryCostAccount> deliveryCostAccountMap;
    @EventSourcedMember
    private PaymentReceivedAccount paymentReceivedAccount;

    //TODO: What about subscriber id (i.e. same subscriber and multiple subscriptions) - will there be repository for that too?

    public Payment() {
    }

    public Payment(String subscriptionId, Double totalBalance) {
        apply(new PaymentInitiatedEvent(subscriptionId, totalBalance));
    }

    @EventSourcingHandler
    public void on(PaymentInitiatedEvent event) {
        this.subscriptionId = event.getSubscriptionId();
        this.paymentReceivedAccount = new PaymentReceivedAccount(event.getTotalBalance());
        this.deliveryCostAccountMap = new HashMap<>();
    }

    public void handlePartialPayment(Double partialPayment) {
        this.paymentReceivedAccount.fireCreditedEvent(this.subscriptionId, partialPayment);
    }

    public void handleDeliveryStatusAndDispatchDateUpdatedCommand(DeliveryStatusAndDispatchDateUpdatedCommand command) {
        this.deliveryCostAccountMap.get(command.getBasketId()).fireCreditedEvent(command.getBasketId(), command.getDeliveryCharges() + command.getTotalDeliveryPrice());
        this.paymentReceivedAccount.fireDebitedEvent(command.getSubscriptionId(), command.getDeliveryCharges() + command.getTotalDeliveryPrice());
    }

    public void handleDeliveryCreatedCommand(DeliveryCreatedCommand command) {
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
