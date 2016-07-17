package com.affaince.subscription.expensedistribution.processor;

import com.affaince.subscription.expensedistribution.client.ExpenseDistributionClient;
import com.affaince.subscription.expensedistribution.query.view.DeliveryItem;
import com.affaince.subscription.expensedistribution.query.view.DeliveryView;
import com.affaince.subscription.expensedistribution.vo.ProductWiseDeliveryStats;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rsavaliya on 24/3/16.
 */
public class DefaultOperatingExpenseDistributionDeterminator implements OperatingExpenseDistribution {

    @Autowired
    private ExpenseDistributionClient expenseDistributionClient;

    public Map<String, Double> distributeDeliveryExpensesToProduct () throws IOException {
        final List<DeliveryView> deliveries = expenseDistributionClient.fetchAllDeliveries();
        final List<ProductWiseDeliveryStats> productWiseDeliveriesStats = new ArrayList<>();
        final Map <String, Double> perUnitProductExpensesMap = new HashMap<>();

        for (DeliveryView deliveryView : deliveries) {
            for (DeliveryItem deliveryItem: deliveryView.getDeliveryItems()) {
                ProductWiseDeliveryStats productWiseDeliveryStats = new ProductWiseDeliveryStats(deliveryItem.getDeliveryItemId());
                if (productWiseDeliveriesStats.contains(productWiseDeliveryStats)) {
                    productWiseDeliveryStats = productWiseDeliveriesStats.get(productWiseDeliveriesStats.indexOf(productWiseDeliveryStats));
                }
                productWiseDeliveryStats.addDeliveryExpense(deliveryItem.getDeliveryCharges());
                productWiseDeliveryStats.addMRP(deliveryItem.getOfferedPriceWithBasketLevelDiscount());
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
