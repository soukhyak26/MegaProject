package com.affaince.subscription.expensedistribution.processor;

import com.affaince.subscription.expensedistribution.query.DeliveryView;
import com.affaince.subscription.expensedistribution.vo.ProductWiseDeliveryStats;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rsavaliya on 20/3/16.
 */
public class ProductWiseTotalDeliveryExpenseStrategy implements AggregationStrategy {
    private List<ProductWiseDeliveryStats> productWiseDeliveryStats = new ArrayList<>();
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        final DeliveryView deliveryView = newExchange.getIn().getBody(DeliveryView.class);

        return null;
    }
}
