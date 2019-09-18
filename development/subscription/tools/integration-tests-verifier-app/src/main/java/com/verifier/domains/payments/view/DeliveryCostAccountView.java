package com.verifier.domains.payments.view;

import com.affaince.subscription.common.deserializer.LocalDateDeserializer;
import com.affaince.subscription.common.serializer.LocalDateSerializer;
import com.affaince.subscription.common.vo.CompositeDeliveryId;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 5/18/2017.
 */
@Document(collection="DeliveryCostAccountView")
public class DeliveryCostAccountView {
    @Id
    private CompositeDeliveryId compositeDeliveryId;
    private String subscriptionId;
    private int deliverySequence;
    private double deliveryAmount;
    private double paymentReceived;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate deliveryDate;

    public DeliveryCostAccountView() {
    }

    public DeliveryCostAccountView(String compositeDeliveryId, String subscriberId, String subscriptionId, int deliverySequence, double deliveryAmount, LocalDate deliveryDate) {
        this.compositeDeliveryId = new CompositeDeliveryId(compositeDeliveryId,subscriberId,subscriptionId);
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

    public CompositeDeliveryId getCompositeDeliveryId() {
        return compositeDeliveryId;
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
    public void setOrOverride(double amount){
        this.deliveryAmount= amount;
    }

}
