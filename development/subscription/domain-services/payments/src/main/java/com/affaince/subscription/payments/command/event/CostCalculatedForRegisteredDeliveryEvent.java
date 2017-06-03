package com.affaince.subscription.payments.command.event;

import com.affaince.subscription.payments.vo.DeliveryDetails;
import org.joda.time.LocalDate;

/**
 * Created by mandar on 5/17/2017.
 */
public class CostCalculatedForRegisteredDeliveryEvent {
    private String subscriptionId;
    private String deliveryId;
    private LocalDate deliveryDate;
    private DeliveryDetails deliveryDetails;
    private double totalDeliveryCost;
    public CostCalculatedForRegisteredDeliveryEvent(String subscriptionId, String deliveryId, LocalDate deliveryDate, DeliveryDetails deliveyDetails, double totalDeliveryCost) {
        this.deliveryId=deliveryId;
        this.subscriptionId=subscriptionId;
        this.deliveryDate=deliveryDate;
        this.deliveryDetails=deliveyDetails;
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

    public DeliveryDetails getDeliveryDetails() {
        return deliveryDetails;
    }
}
