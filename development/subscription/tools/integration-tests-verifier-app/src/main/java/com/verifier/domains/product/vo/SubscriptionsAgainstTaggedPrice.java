package com.verifier.domains.product.vo;

import com.affaince.subscription.common.vo.PriceTaggedWithProduct;

/**
 * Created by mandar on 4/5/2017.
 */
public class SubscriptionsAgainstTaggedPrice {
    private PriceTaggedWithProduct taggedPriceVersion;
    private long numberOfNewSubscriptions;
    private long numberOfChurnedSubscriptions;


    public SubscriptionsAgainstTaggedPrice(PriceTaggedWithProduct taggedPriceVersion) {
        this.taggedPriceVersion = taggedPriceVersion;
    }

    public void addToNewSubscriptions(long subscriptionCount){
        numberOfNewSubscriptions += subscriptionCount;
    }

    private void addToChurnedSubscriptions(long subscriptionCount){
        numberOfChurnedSubscriptions +=subscriptionCount;
    }

    public PriceTaggedWithProduct getTaggedPriceVersion() {
        return taggedPriceVersion;
    }

    public long getNumberOfNewSubscriptions() {
        return numberOfNewSubscriptions;
    }

    public long getNumberOfChurnedSubscriptions() {
        return numberOfChurnedSubscriptions;
    }

}
