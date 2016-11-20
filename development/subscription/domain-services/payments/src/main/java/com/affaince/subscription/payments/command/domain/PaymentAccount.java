package com.affaince.subscription.payments.command.domain;

import com.affaince.subscription.payments.command.DeliveryCreatedCommand;
import com.affaince.subscription.payments.command.DeliveryDeletedCommand;
import com.affaince.subscription.payments.command.DeliveryStatusAndDispatchDateUpdatedCommand;
import com.affaince.subscription.payments.command.accounting.*;
import com.affaince.subscription.payments.command.event.DeliveryDestroyedEvent;
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
public class PaymentAccount extends AbstractAnnotatedAggregateRoot<String> {

    @AggregateIdentifier
    private String subscriptionId;
    @EventSourcedMember
    private Map<String, DeliveryCostAccount> deliveryCostAccountMap;
    @EventSourcedMember
    private TotalBalanceAccount totalBalanceAccount;
    @EventSourcedMember
    //TODO: for below account, should delivery price and charge be part of DeliveryCreatedEvent/Command?
    private TotalSubscriptionCostAccount totalSubscriptionCostAccount;
    @EventSourcedMember
    private TotalReceivableCostAccount totalReceivableCostAccount;
    @EventSourcedMember
    private TotalReceivedCostAccount totalReceivedCostAccount;

    /**
     * Basically, when subscription cost increases, receivable increases
     * When balance increases, recievable decreases
     * At the end, everything balance and recievable should be 0
     */

    //TODO: What about subscriber id (i.e. same subscriber and multiple subscriptions) - will there be repository for that too?

    public PaymentAccount() {
    }

    public PaymentAccount(String subscriptionId, Double totalBalance) {
        apply(new PaymentInitiatedEvent(subscriptionId, totalBalance));
    }

    @EventSourcingHandler
    public void on(PaymentInitiatedEvent event) {
        this.subscriptionId = event.getSubscriptionId();
        this.totalBalanceAccount = new TotalBalanceAccount(event.getTotalBalance());
        this.deliveryCostAccountMap = new HashMap<>();
        this.totalSubscriptionCostAccount = new TotalSubscriptionCostAccount(0);
        this.totalReceivableCostAccount = new TotalReceivableCostAccount(0 - event.getTotalBalance());
        this.totalReceivedCostAccount = new TotalReceivedCostAccount(event.getTotalBalance());
        this.totalReceivedCostAccount = new TotalReceivedCostAccount(event.getTotalBalance());
    }

    public void handlePartialPayment(Double partialPayment) {
        this.totalBalanceAccount.fireCreditedEvent(this.subscriptionId, partialPayment);
        this.totalReceivableCostAccount.fireDebitedEvent(this.subscriptionId, partialPayment);
        this.totalReceivedCostAccount.fireCreditedEvent(this.subscriptionId, partialPayment);
    }

    public void handleDeliveryStatusAndDispatchDateUpdatedCommand(DeliveryStatusAndDispatchDateUpdatedCommand command) {
        switch (command.getBasketDeliveryStatus()) {
            case CREATED:
                this.totalSubscriptionCostAccount.fireCreditedEvent(command.getSubscriptionId(), command.getDeliveryCharges() + command.getTotalDeliveryPrice());
                this.totalReceivableCostAccount.fireCreditedEvent(command.getSubscriptionId(), command.getDeliveryCharges() + command.getTotalDeliveryPrice());
                break;
            case DELIVERED:
                this.deliveryCostAccountMap.get(command.getBasketId()).fireCreditedEvent(command.getBasketId(), command.getDeliveryCharges() + command.getTotalDeliveryPrice());
                this.totalBalanceAccount.fireDebitedEvent(command.getSubscriptionId(), command.getDeliveryCharges() + command.getTotalDeliveryPrice());
                //here receivable will not increase because payment is already received in terms of balance
                //this.totalReceivableCostAccount.fireCreditedEvent(command.getSubscriptionId(), command.getDeliveryCharges() + command.getTotalDeliveryPrice());
                break;
        }
    }

    public void handleDeliveryCreatedCommand(DeliveryCreatedCommand command) {
        apply(new DeliveryInitiatedEvent(command.getDeliveryId(), command.getSubscriptionId()));
    }

    public void handleDeliveryDeletedCommand(DeliveryDeletedCommand command) {
        apply(new DeliveryDestroyedEvent(command.getDeliveryId(), command.getSubscriberId()));
    }

    @EventSourcingHandler
    public void on(DeliveryInitiatedEvent event) {
        //TODO: change 0 to actual amount for delivery cost account
        this.deliveryCostAccountMap.put(event.getDeliveryId(), new DeliveryCostAccount(0));
        this.totalReceivableCostAccount.fireCreditedEvent(event.getSubscriptionId(), 0);
    }

    @EventSourcingHandler
    public void on(DeliveryDestroyedEvent event) {
        //TODO: change 0 to actual amount
        this.deliveryCostAccountMap.remove(event.getDeliveryId());
        this.totalReceivableCostAccount.fireDebitedEvent(event.getSubscriptionId(), 0);
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Map<String, DeliveryCostAccount> getDeliveryCostAccountMap() {
        return deliveryCostAccountMap;
    }

    public void setDeliveryCostAccountMap(Map<String, DeliveryCostAccount> deliveryCostAccountMap) {
        this.deliveryCostAccountMap = deliveryCostAccountMap;
    }

    public TotalBalanceAccount getTotalBalanceAccount() {
        return totalBalanceAccount;
    }

    public void setTotalBalanceAccount(TotalBalanceAccount totalBalanceAccount) {
        this.totalBalanceAccount = totalBalanceAccount;
    }

    public TotalSubscriptionCostAccount getTotalSubscriptionCostAccount() {
        return totalSubscriptionCostAccount;
    }

    public TotalReceivableCostAccount getTotalReceivableCostAccount() {
        return totalReceivableCostAccount;
    }

    public TotalReceivedCostAccount getTotalReceivedCostAccount() {
        return totalReceivedCostAccount;
    }
}
