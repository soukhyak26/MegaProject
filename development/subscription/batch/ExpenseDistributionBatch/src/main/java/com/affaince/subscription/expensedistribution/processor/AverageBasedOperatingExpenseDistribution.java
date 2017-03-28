package com.affaince.subscription.expensedistribution.processor;

import com.affaince.subscription.expensedistribution.client.ExpenseDistributionClient;
import com.affaince.subscription.expensedistribution.query.view.DeliveryItem;
import com.affaince.subscription.expensedistribution.query.view.DeliveryView;
import com.affaince.subscription.expensedistribution.query.view.ProductForecastView;
import com.affaince.subscription.expensedistribution.vo.ProductWiseDeliveryStats;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rbsavaliya on 26-06-2016.
 */
public class AverageBasedOperatingExpenseDistribution {

    @Autowired
    private ExpenseDistributionClient expenseDistributionClient;

    public Map<String, Double> distributeDeliveryExpensesToProduct (final List <String> productIds) throws IOException {
        final Iterable<DeliveryView> deliveries = expenseDistributionClient.fetchAllDeliveries();
        final List<ProductWiseDeliveryStats> productWiseDeliveriesStats = new ArrayList<>();
        final Map <String, Double> perUnitProductExpensesMap = new HashMap<>();
        double totalDeliveryExpense = 0;
        double totalOfferedPrice = 0;

        for (DeliveryView deliveryView : deliveries) {
            for (DeliveryItem deliveryItem: deliveryView.getDeliveryItems()) {
                totalDeliveryExpense += deliveryItem.getDeliveryCharges();
                totalOfferedPrice += deliveryItem.getOfferedPricePerUnit();
            }
        }
        final Map<String, ProductWiseDeliveryStats> productWiseDeliveryStatsMap =
                createYearlyProductWiseDeliveryStats (productIds);

        for (String productId: productWiseDeliveryStatsMap.keySet()) {
            ProductWiseDeliveryStats productWiseDeliveryStats = productWiseDeliveryStatsMap.get(productId);
            perUnitProductExpensesMap.put(
                    productId,
                    ((productWiseDeliveryStats.getTotalMRP()*totalDeliveryExpense)/totalOfferedPrice)
                    /productWiseDeliveryStats.getTotalUnitsSold()
            );
        }
        return perUnitProductExpensesMap;
    }

    private Map<String, ProductWiseDeliveryStats> createYearlyProductWiseDeliveryStats(final List<String> productIds) throws IOException {
        final Map<String, ProductWiseDeliveryStats> productWiseYearlyDeliveryStats
                = new HashMap<>(productIds.size());
        for (String productId: productIds) {
            final List<ProductForecastView> productForecastViews =
                    expenseDistributionClient.fetchProductForecastByProductId(productId);
            ProductWiseDeliveryStats productWiseDeliveryStats = new ProductWiseDeliveryStats(productId);
            productWiseYearlyDeliveryStats.put(productId, productWiseDeliveryStats);
            for (ProductForecastView productForecastView : productForecastViews) {
               // productWiseDeliveryStats.addMRP(productForecastMetricsView.getTaggedPriceVersions().first().getMRP());
                productWiseDeliveryStats.addMRP(80);
                productWiseDeliveryStats.addUnitSold(
                        productForecastView.getTotalNumberOfExistingSubscriptions()
                );
            }
        }
        return productWiseYearlyDeliveryStats;
    }
}
