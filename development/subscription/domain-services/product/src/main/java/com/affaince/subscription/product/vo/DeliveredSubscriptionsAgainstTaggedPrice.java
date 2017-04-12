package com.affaince.subscription.product.vo;

import com.affaince.subscription.common.vo.PriceTaggedWithProduct;
import org.joda.time.DateTimeComparator;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mandar on 4/6/2017.
 */
public class DeliveredSubscriptionsAgainstTaggedPrice {
    private PriceTaggedWithProduct taggedPriceVersion;
    private long numberOfDeliveredSubscriptions;
    @Autowired
    DateTimeComparator dateTimeComparator;

    public DeliveredSubscriptionsAgainstTaggedPrice(PriceTaggedWithProduct taggedPriceVersion) {
        this.taggedPriceVersion = taggedPriceVersion;
    }

    public PriceTaggedWithProduct getTaggedPriceVersion() {
        return taggedPriceVersion;
    }

    public long getNumberOfDeliveredSubscriptions() {
        return numberOfDeliveredSubscriptions;
    }

    public void addToDeliveredSubscriptions(long subscriptionCount){
        this.numberOfDeliveredSubscriptions += subscriptionCount;
    }

}
