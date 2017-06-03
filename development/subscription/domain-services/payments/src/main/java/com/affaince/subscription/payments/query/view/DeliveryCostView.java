package com.affaince.subscription.payments.query.view;

import com.affaince.subscription.payments.vo.SubscriptionwiseDeliveryId;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 5/18/2017.
 */
@Document(collection="DeliveryCostView")
public class DeliveryCostView {
    @Id
    private SubscriptionwiseDeliveryId subscriptionwiseDeliveryId;
    private String subscriptionId;
    private int deliverySequence;
    private double deliveryAmount;
    private double paymentReceived;
    private LocalDate deliveryDate;

    public DeliveryCostView(String deliveryId, String subscriptionId, int deliverySequence, double deliveryAmount,LocalDate deliveryDate) {
        this.subscriptionwiseDeliveryId = new SubscriptionwiseDeliveryId(subscriptionId,deliveryId);
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

    public SubscriptionwiseDeliveryId getSubscriptionwiseDeliveryId() {
        return subscriptionwiseDeliveryId;
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
