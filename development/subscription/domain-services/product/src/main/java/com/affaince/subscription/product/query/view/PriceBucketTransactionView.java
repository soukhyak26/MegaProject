package com.affaince.subscription.product.query.view;

import com.affaince.subscription.product.vo.PriceBucketTransactionId;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandar on 5/2/2017.
 */
@Document(collection="PriceBucketTransactionView")
public class PriceBucketTransactionView {
    private final PriceBucketTransactionId priceBucketTransactionId;
    private long numberOfNewSubscriptions;
    private long numberOfChurnedSubscriptions;
    private long numberOfExistingSubscriptions;

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
}
