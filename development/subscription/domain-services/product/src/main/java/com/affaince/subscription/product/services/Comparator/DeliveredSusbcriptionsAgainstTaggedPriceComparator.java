package com.affaince.subscription.product.services.Comparator;

import com.affaince.subscription.product.vo.DeliveredSubscriptionsAgainstTaggedPrice;

import java.util.Comparator;

/**
 * Created by mandar on 4/8/2017.
 */
public class DeliveredSusbcriptionsAgainstTaggedPriceComparator implements Comparator<DeliveredSubscriptionsAgainstTaggedPrice> {
    @Override
    public int compare(DeliveredSubscriptionsAgainstTaggedPrice source, DeliveredSubscriptionsAgainstTaggedPrice destination) {
        return source.getTaggedPriceVersion().getTaggedStartDate().compareTo(destination.getTaggedPriceVersion().getTaggedStartDate());
    }

}
