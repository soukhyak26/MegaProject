package com.affaince.subscription.product.services.Comparator;

import com.affaince.subscription.product.vo.SubscriptionsAgainstTaggedPrice;

import java.util.Comparator;

/**
 * Created by mandar on 4/8/2017.
 */
public class SubscriptionsAgainstTaggedPriceDescComparator implements Comparator<SubscriptionsAgainstTaggedPrice> {

    @Override
    public int compare(SubscriptionsAgainstTaggedPrice source, SubscriptionsAgainstTaggedPrice destination) {
        return destination.getTaggedPriceVersion().getTaggedStartDate().compareTo(source.getTaggedPriceVersion().getTaggedStartDate());
    }

}
