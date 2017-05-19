package com.affaince.subscription.payments.command.event;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 5/17/2017.
 */
public class CostCalculatedForRegisteredDeliveryEvent {
    private String subscriptionId;
    private String deliveryId;
    private LocalDate deliveryDate;
    private double totalDeliveryCost;
    public CostCalculatedForRegisteredDeliveryEvent(String subscriptionId, String deliveryId, LocalDate deliveryDate, double totalDeliveryCost) {
        this.deliveryId=deliveryId;
        this.subscriptionId=subscriptionId;
        this.deliveryDate=deliveryDate;
        this.totalDeliveryCost=totalDeliveryCost;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public double getTotalDeliveryCost() {
        return totalDeliveryCost;
    }
}
