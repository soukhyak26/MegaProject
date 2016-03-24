package com.affaince.subscription.expensedistribution.processor;

import com.affaince.subscription.expensedistribution.query.DeliveryItem;
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
    private List<ProductWiseDeliveryStats> productWiseDeliveriesStats = new ArrayList<>();
    private double totalDeliveryExpenses = 0.0;
    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        final DeliveryView deliveryView = newExchange.getIn().getBody(DeliveryView.class);
        if (oldExchange == null) {
            productWiseDeliveriesStats = new ArrayList<>();
        }
        for (DeliveryItem deliveryItem: deliveryView.getDeliveryItems()) {
            ProductWiseDeliveryStats productWiseDeliveryStats = new ProductWiseDeliveryStats(deliveryItem.getDeliveryItemId());
            if (productWiseDeliveriesStats.contains(productWiseDeliveryStats)) {
                productWiseDeliveryStats = productWiseDeliveriesStats.get(productWiseDeliveriesStats.indexOf(productWiseDeliveryStats));
            }
            productWiseDeliveryStats.addDeliveryExpense(deliveryItem.getDeliveryCharges());
            productWiseDeliveryStats.addOfferedPrice(deliveryItem.getOfferedPriceWithBasketLevelDiscount());
            productWiseDeliveryStats.addUnitSold(1);
            productWiseDeliveriesStats.add(productWiseDeliveryStats);
            totalDeliveryExpenses += deliveryItem.getDeliveryCharges();
        }
        newExchange.getIn().setBody(productWiseDeliveriesStats);
        newExchange.getIn().setBody(totalDeliveryExpenses);
        return newExchange;
    }
}
