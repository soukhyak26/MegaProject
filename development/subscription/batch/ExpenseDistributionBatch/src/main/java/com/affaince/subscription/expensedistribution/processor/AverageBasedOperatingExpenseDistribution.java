package com.affaince.subscription.expensedistribution.processor;

import com.affaince.subscription.expensedistribution.query.repository.DeliveryViewRepository;
import com.affaince.subscription.expensedistribution.query.repository.ForecastPriceBucketsViewRepository;
import com.affaince.subscription.expensedistribution.query.view.DeliveryItem;
import com.affaince.subscription.expensedistribution.query.view.DeliveryView;
import com.affaince.subscription.expensedistribution.query.view.ForecastPriceBucketsView;
import com.affaince.subscription.expensedistribution.vo.ProductWiseDeliveryStats;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rbsavaliya on 26-06-2016.
 */
public class AverageBasedOperatingExpenseDistribution {
    @Autowired
    private DeliveryViewRepository deliveryViewRepository;
    @Autowired
    private ForecastPriceBucketsViewRepository forecastPriceBucketsViewRepository;

    public Map<String, Double> distributeDeliveryExpensesToProduct (final List <String> productIds) {
        final Iterable<DeliveryView> deliveries = deliveryViewRepository.findAll();
        final List<ProductWiseDeliveryStats> productWiseDeliveriesStats = new ArrayList<>();
        final Map <String, Double> perUnitProductExpensesMap = new HashMap<>();
        double totalDeliveryExpense = 0;
        double totalOfferedPrice = 0;

        for (DeliveryView deliveryView : deliveries) {
            for (DeliveryItem deliveryItem: deliveryView.getDeliveryItems()) {
                totalDeliveryExpense += deliveryItem.getDeliveryCharges();
                totalOfferedPrice += deliveryItem.getOfferedPriceWithBasketLevelDiscount();
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

    private Map<String, ProductWiseDeliveryStats> createYearlyProductWiseDeliveryStats(final List <String> productIds) {
        final Map<String, ProductWiseDeliveryStats> productWiseYearlyDeliveryStats
                = new HashMap<>(productIds.size());
        for (String productId: productIds) {
            final List <ForecastPriceBucketsView> forecastPriceBucketsViews =
                    forecastPriceBucketsViewRepository.findByProductId(productId);
            ProductWiseDeliveryStats productWiseDeliveryStats = new ProductWiseDeliveryStats(productId);
            productWiseYearlyDeliveryStats.put(productId, productWiseDeliveryStats);
            for (ForecastPriceBucketsView forecastPriceBucketsView : forecastPriceBucketsViews) {
                productWiseDeliveryStats.addMRP(forecastPriceBucketsView.getMRP());
                productWiseDeliveryStats.addUnitSold(
                        forecastPriceBucketsView.getNumberOfExistingCustomersAssociatedWithAPrice()
                );
            }
        }
        return productWiseYearlyDeliveryStats;
    }
}
