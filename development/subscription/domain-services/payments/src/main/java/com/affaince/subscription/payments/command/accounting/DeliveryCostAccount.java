package com.affaince.subscription.payments.command.accounting;

import com.affaince.subscription.payments.vo.DeliveryDetails;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

public class DeliveryCostAccount extends Account {
    private String deliveryId;
    private String subscriptionId;
    private int sequence;
    private double paymentReceived;
    private DeliveryDetails deliveryDetail;

    public DeliveryCostAccount(String deliveryId, String subscriptionId,int sequence, LocalDate deliveryDate,DeliveryDetails deliveryDetail, double totalDeliveryCost) {
        super(totalDeliveryCost,deliveryDate);
        this.deliveryId = deliveryId;
        this.sequence=sequence;
        this.deliveryDetail=deliveryDetail;
        this.subscriptionId = subscriptionId;
    }

    public void creditToPaymentReceived( double payment){
        paymentReceived += payment;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public double getPaymentReceived() {
        return paymentReceived;
    }

    public DeliveryDetails getDeliveryDetail() {
        return deliveryDetail;
    }

    public void setPaymentReceived(double paymentReceived) {
        this.paymentReceived = paymentReceived;
    }

    public void setDeliveryDetail(DeliveryDetails deliveryDetail) {
        this.deliveryDetail = deliveryDetail;
    }
    public boolean isProductInDelivery(String productId){
        return this.deliveryDetail.isProductInDelivery(productId);
    }

    public int getSequence() {
        return sequence;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeliveryCostAccount that = (DeliveryCostAccount) o;

        if (!deliveryId.equals(that.deliveryId)) return false;
        return subscriptionId.equals(that.subscriptionId);

    }

    @Override
    public int hashCode() {
        int result = deliveryId.hashCode();
        result = 31 * result + subscriptionId.hashCode();
        return result;
    }
}
