package com.affaince.subscription.payments.query.view;

import com.affaince.subscription.common.vo.DeliveryId;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 5/18/2017.
 */
@Document(collection="DeliveryCostView")
public class DeliveryCostView {
    @Id
    private DeliveryId deliveryId;
    private String subscriptionId;
    private int deliverySequence;
    private double deliveryAmount;
    private double paymentReceived;
    private LocalDate deliveryDate;

    public DeliveryCostView(String deliveryId, String subscriberId,String subscriptionId, int deliverySequence, double deliveryAmount,LocalDate deliveryDate) {
        this.deliveryId = new DeliveryId(deliveryId,subscriberId,subscriptionId);
        this.subscriptionId = subscriptionId;
        this.deliverySequence = deliverySequence;
        this.deliveryAmount = deliveryAmount;
        this.deliveryDate=deliveryDate;
    }

    public void credit(double amount) {
        this.deliveryAmount += amount;
    }
    public void creditToPaymentReceived( double payment){
        paymentReceived += payment;
    }
    public void debit(double amount) {
        this.deliveryAmount  -= amount;
    }

    public DeliveryId getDeliveryId() {
        return deliveryId;
    }

    public double getPaymentReceived() {
        return paymentReceived;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public int getDeliverySequence() {
        return deliverySequence;
    }

    public double getDeliveryAmount() {
        return deliveryAmount;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }
}
