package com.affaince.subscription.expensedistribution.processor;

import com.affaince.subscription.expensedistribution.vo.ProductWiseDeliveryStats;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rbsavaliya on 19-06-2016.
 */
public class ProductWiseDeliveryStatsAggregation {

    public List<ProductWiseDeliveryStats> aggregate (List<ProductWiseDeliveryStats> productWiseDeliveryStatses) {
        final List <ProductWiseDeliveryStats> deliveryStats = new ArrayList<>();
        deliveryStats.addAll(productWiseDeliveryStatses);
        return deliveryStats;
    }
}
