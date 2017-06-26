package com.affaince.subscription.product.query.view;

import com.affaince.subscription.product.vo.PriceBucketTransactionId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 5/2/2017.
 */
@Document(collection="PriceBucketTransactionView")
public class PriceBucketTransactionView {
    @Id
    private final PriceBucketTransactionId priceBucketTransactionId;
    private long numberOfNewSubscriptions;
    private long numberOfChurnedSubscriptions;
    private long numberOfExistingSubscriptions;
    private double offeredPrice;
    private double purchasePrice;
    private double MRP;

    public PriceBucketTransactionView(PriceBucketTransactionId priceBucketTransactionId) {
        this.priceBucketTransactionId = priceBucketTransactionId;
    }

    public PriceBucketTransactionId getPriceBucketTransactionId() {
        return priceBucketTransactionId;
    }

    public long getNumberOfNewSubscriptions() {
        return numberOfNewSubscriptions;
    }

    public long getNumberOfChurnedSubscriptions() {
        return numberOfChurnedSubscriptions;
    }

    public long getNumberOfExistingSubscriptions() {
        return numberOfExistingSubscriptions;
    }

    public void addToNewSubscriptions(long subscriptionCount){
        this.numberOfNewSubscriptions += subscriptionCount;
        this.numberOfExistingSubscriptions +=subscriptionCount;
    }

    public void addToChurnedSubscriptions(long subscriptionCount){
        this.numberOfChurnedSubscriptions += subscriptionCount;
        this.numberOfExistingSubscriptions -=subscriptionCount;
    }

    public double getOfferedPrice() {
        return offeredPrice;
    }

    public void setOfferedPrice(double offeredPrice) {
        this.offeredPrice = offeredPrice;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getMRP() {
        return MRP;
    }

    public void setMRP(double MRP) {
        this.MRP = MRP;
    }

    public void setNumberOfNewSubscriptions(long numberOfNewSubscriptions) {
        this.numberOfNewSubscriptions = numberOfNewSubscriptions;
    }

    public void setNumberOfChurnedSubscriptions(long numberOfChurnedSubscriptions) {
        this.numberOfChurnedSubscriptions = numberOfChurnedSubscriptions;
    }

    public void setNumberOfExistingSubscriptions(long numberOfExistingSubscriptions) {
        this.numberOfExistingSubscriptions = numberOfExistingSubscriptions;
    }
}
