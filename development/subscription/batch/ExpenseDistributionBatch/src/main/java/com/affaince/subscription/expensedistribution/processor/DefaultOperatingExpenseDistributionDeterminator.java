package com.affaince.subscription.expensedistribution.processor;

import com.affaince.subscription.expensedistribution.query.DeliveryItem;
import com.affaince.subscription.expensedistribution.query.DeliveryView;
import com.affaince.subscription.expensedistribution.query.DeliveryViewRepository;
import com.affaince.subscription.expensedistribution.vo.ProductWiseDeliveryStats;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rsavaliya on 24/3/16.
 */
public class DefaultOperatingExpenseDistributionDeterminator {

    @Autowired
    private DeliveryViewRepository deliveryViewRepository;

    public Map<String, Double> distributeDeliveryExpensesToProduct () {
        final Iterable<DeliveryView> deliveries = deliveryViewRepository.findAll();
        final List<ProductWiseDeliveryStats> productWiseDeliveriesStats = new ArrayList<>();
        final Map <String, Double> perUnitProductExpensesMap = new HashMap<>();

        for (DeliveryView deliveryView : deliveries) {
            for (DeliveryItem deliveryItem: deliveryView.getDeliveryItems()) {
                ProductWiseDeliveryStats productWiseDeliveryStats = new ProductWiseDeliveryStats(deliveryItem.getDeliveryItemId());
                if (productWiseDeliveriesStats.contains(productWiseDeliveryStats)) {
                    productWiseDeliveryStats = productWiseDeliveriesStats.get(productWiseDeliveriesStats.indexOf(productWiseDeliveryStats));
                }
                productWiseDeliveryStats.addDeliveryExpense(deliveryItem.getDeliveryCharges());
                productWiseDeliveryStats.addOfferedPrice(deliveryItem.getOfferedPriceWithBasketLevelDiscount());
                productWiseDeliveryStats.addUnitSold(1);
                productWiseDeliveriesStats.add(productWiseDeliveryStats);
            }
        }

        for (ProductWiseDeliveryStats productWiseDeliveryStats: productWiseDeliveriesStats) {
            perUnitProductExpensesMap.put(
                    productWiseDeliveryStats.getProductId(),
                    productWiseDeliveryStats.getTotalDeliveryExpense()/productWiseDeliveryStats.getTotalUnitsSold()
            );
        }
        return perUnitProductExpensesMap;
    }
}
