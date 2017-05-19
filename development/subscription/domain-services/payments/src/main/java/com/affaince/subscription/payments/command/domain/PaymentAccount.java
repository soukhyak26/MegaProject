package com.affaince.subscription.payments.command.domain;

import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.payments.command.DeliveryCreatedCommand;
import com.affaince.subscription.payments.command.DeliveryDeletedCommand;
import com.affaince.subscription.payments.command.DeliveryStatusAndDispatchDateUpdatedCommand;
import com.affaince.subscription.payments.command.accounting.*;
import com.affaince.subscription.payments.command.event.*;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcedMember;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PaymentAccount extends AbstractAnnotatedAggregateRoot<String> {

    @AggregateIdentifier
    private String subscriptionId;

    private String subscriberId;
    @EventSourcedMember
    private Map<String, DeliveryCostAccount> deliveryCostAccountMap;
    @EventSourcedMember
    //TODO: for below account, should delivery price and charge be part of DeliveryCreatedEvent/Command?
    private TotalSubscriptionCostAccount totalSubscriptionCostAccount;
    @EventSourcedMember
    private TotalReceivableCostAccount totalReceivableCostAccount;
    @EventSourcedMember
    private TotalReceivedCostAccount totalReceivedCostAccount;

    /**
     * When subscription cost increases, receivable increases
     * When balance increases, recievable decreases
     * At the end, everything balance and recievable should be 0
     */


    public PaymentAccount() {
    }

    public PaymentAccount(String subscriberId, String subscriptionId) {
        apply(new PaymentAccountCreatedEvent(subscriberId,subscriptionId));
    }

    public PaymentAccount(String subscriptionId, Double totalBalance) {
        apply(new PaymentInitiatedEvent(subscriptionId, totalBalance));
    }

    @EventSourcingHandler
    public void on(PaymentInitiatedEvent event) {
    }


    public void handleDeliveryCreatedCommand(DeliveryCreatedCommand command) {
        apply(new DeliveryInitiatedEvent(command.getDeliveryId(), command.getSubscriptionId()));
    }

    public void handleDeliveryDeletedCommand(DeliveryDeletedCommand command) {
        apply(new DeliveryDestroyedEvent(command.getDeliveryId(), command.getSubscriberId()));
    }

    @EventSourcingHandler
    public void on(DeliveryInitiatedEvent event) {
    }

    @EventSourcingHandler
    public void on(DeliveryDestroyedEvent event) {
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


    public TotalSubscriptionCostAccount getTotalSubscriptionCostAccount() {
        return totalSubscriptionCostAccount;
    }

    public TotalReceivableCostAccount getTotalReceivableCostAccount() {
        return totalReceivableCostAccount;
    }

    public TotalReceivedCostAccount getTotalReceivedCostAccount() {
        return totalReceivedCostAccount;
    }

    public void registerSubscriptionDetails(List<DeliveryCreatedEvent> totalSubscriptionDeliveries) {
        double totalTentativeSubscriptionAmount=0;
        double totalRewardPoints=0;
        for(DeliveryCreatedEvent delivery: totalSubscriptionDeliveries){
            List<DeliveryItem> itemsInADelivery= delivery.getDeliveryItems();
            totalRewardPoints +=delivery.getRewardPoints();
            double totalDeliveryCost=0;
            for(DeliveryItem item:itemsInADelivery){
                totalTentativeSubscriptionAmount+=item.getOfferedPriceWithBasketLevelDiscount();
                totalDeliveryCost +=item.getOfferedPriceWithBasketLevelDiscount();
            }
            apply(new CostCalculatedForRegisteredDeliveryEvent(this.getSubscriptionId(),delivery.getDeliveryId(),delivery.getDeliveryDate(),totalDeliveryCost));
        }

        apply(new SubscriptionDetailsRegisteredEvent(this.getSubscriptionId(),totalTentativeSubscriptionAmount,totalRewardPoints, SysDate.now()));
    }

    @EventSourcingHandler
    public void on (PaymentAccountCreatedEvent event){
        this.subscriptionId = event.getSubscriptionId();
        this.subscriberId=event.getSubscriberId();
        this.deliveryCostAccountMap = new HashMap<>();
    }
    @EventSourcingHandler
    public void on(CostCalculatedForRegisteredDeliveryEvent event){
        DeliveryCostAccount deliveryCostAccount = new DeliveryCostAccount(event.getDeliveryId(),event.getSubscriptionId(),event.getDeliveryDate(),event.getTotalDeliveryCost());
        this.deliveryCostAccountMap.put(event.getDeliveryId(),deliveryCostAccount);
    }

    @EventSourcingHandler
    public void on(SubscriptionDetailsRegisteredEvent event){
        this.totalReceivableCostAccount=new TotalReceivableCostAccount(event.getSubscriptionId(),event.getTotalTentativeSubscriptionAmount(),event.getRegistrationDate());
        this.totalReceivedCostAccount= new TotalReceivedCostAccount(event.getSubscriptionId(),0,event.getRegistrationDate());
        this.totalSubscriptionCostAccount= new TotalSubscriptionCostAccount(event.getSubscriptionId(),event.getTotalTentativeSubscriptionAmount(),event.getRegistrationDate());
    }
}
