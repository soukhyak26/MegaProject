package com.affaince.subscription.expensedistribution.processor;

import com.affaince.subscription.common.service.MathsProcessingService;
import com.affaince.subscription.expensedistribution.client.ExpenseDistributionClient;
import com.affaince.subscription.expensedistribution.query.view.DeliveryItem;
import com.affaince.subscription.expensedistribution.query.view.DeliveryView;
import com.affaince.subscription.expensedistribution.vo.ProductWiseDeliveryStats;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rsavaliya on 26/3/16.
 */
public class ExtraPolationBasedOperatingExpenseDistributionDeterminator implements OperatingExpenseDistribution {

    @Autowired
    private ExpenseDistributionClient expenseDistributionClient;

    @Override
    public Map<String, Double> distributeDeliveryExpensesToProduct() throws IOException {
        final List<DeliveryView> deliveries = expenseDistributionClient.fetchAllDeliveries();
        Map<String, Map<Integer, ProductWiseDeliveryStats>> productWiseDeliveryStatsMap
                = createMonthlyDeliveryStatsMap(deliveries);
        Map<String, Double> unitWiseDeliveryExpenseMap = new HashMap<>(productWiseDeliveryStatsMap.size());
        for (String productId : productWiseDeliveryStatsMap.keySet()) {
            double extraPolatedExpense = extraPolateExpenses(productWiseDeliveryStatsMap.get(productId));
            double extraPolatedUnitsSold = extraPolateNoOfUnitsSold(productWiseDeliveryStatsMap.get(productId));
            unitWiseDeliveryExpenseMap.put(
                    productId,
                    calculatePerUnitExpense(productWiseDeliveryStatsMap.get(productId), extraPolatedExpense, extraPolatedUnitsSold)
            );
        }
        return unitWiseDeliveryExpenseMap;
    }

    private Double calculatePerUnitExpense(Map<Integer, ProductWiseDeliveryStats> integerProductWiseDeliveryStatsMap, double extraPolatedExpense, double extraPolatedUnitsSold) {
        final double totalExpense = integerProductWiseDeliveryStatsMap.values().stream()
                .mapToDouble(ProductWiseDeliveryStats::getTotalDeliveryExpense).sum() + extraPolatedExpense;
        final double totalNoOfUnitSold = integerProductWiseDeliveryStatsMap.values().stream()
                .mapToDouble(ProductWiseDeliveryStats::getTotalUnitsSold).sum() + extraPolatedUnitsSold;
        return totalExpense / totalNoOfUnitSold;
    }

    private double extraPolateNoOfUnitsSold(Map<Integer, ProductWiseDeliveryStats> integerProductWiseDeliveryStatsMap) {
        MathsProcessingService mathsProcessingService = new MathsProcessingService();
        double[] expenseForecast = mathsProcessingService.
                processForecastUsingTripleExponentialTimeSeries(integerProductWiseDeliveryStatsMap.values().
                                stream().mapToDouble(ProductWiseDeliveryStats::getTotalUnitsSold).toArray()
                        , integerProductWiseDeliveryStatsMap.size());
        return expenseForecast[0];
    }

    private double extraPolateExpenses(Map<Integer, ProductWiseDeliveryStats> integerProductWiseDeliveryStatsMap) {
        MathsProcessingService mathsProcessingService = new MathsProcessingService();
        double[] noOfUnitSoldForecast = mathsProcessingService.
                processForecastUsingTripleExponentialTimeSeries(integerProductWiseDeliveryStatsMap.values().
                                stream().mapToDouble(ProductWiseDeliveryStats::getTotalUnitsSold).toArray()
                        , integerProductWiseDeliveryStatsMap.size());
        ;
        return noOfUnitSoldForecast[0];
    }

    private Map<String, Map<Integer, ProductWiseDeliveryStats>> createMonthlyDeliveryStatsMap(Iterable<DeliveryView> deliveries) {
        final Map<String, Map<Integer, ProductWiseDeliveryStats>> productWiseDeliveryStatsMap =
                new HashMap<>();
        for (DeliveryView deliveryView : deliveries) {
            int deliveryMonth = deliveryView.getDeliveryDate().getMonthOfYear();
            Map<Integer, ProductWiseDeliveryStats> productWiseMonthlyDeliveriesMap;
            for (DeliveryItem deliveryItem : deliveryView.getDeliveryItems()) {
                if (productWiseDeliveryStatsMap.containsKey(deliveryItem.getDeliveryItemId())) {
                    productWiseMonthlyDeliveriesMap = productWiseDeliveryStatsMap.get(deliveryItem.getDeliveryItemId());
                } else {
                    productWiseMonthlyDeliveriesMap = new HashMap<>();
                    productWiseDeliveryStatsMap.put(deliveryItem.getDeliveryItemId(), productWiseMonthlyDeliveriesMap);
                }
                ProductWiseDeliveryStats productWiseDeliveryStats = productWiseMonthlyDeliveriesMap.get(deliveryMonth);
                if (productWiseDeliveryStats == null) {
                    productWiseDeliveryStats = new ProductWiseDeliveryStats(deliveryItem.getDeliveryItemId());
                }
                productWiseDeliveryStats.addDeliveryExpense(deliveryItem.getDeliveryCharges());
                productWiseDeliveryStats.addUnitSold(1);
                productWiseDeliveryStats.addMRP(deliveryItem.getOfferedPricePerUnit());
                productWiseMonthlyDeliveriesMap.put(deliveryMonth, productWiseDeliveryStats);
                productWiseDeliveryStatsMap.put(deliveryItem.getDeliveryItemId(), productWiseMonthlyDeliveriesMap);
            }
        }
        return productWiseDeliveryStatsMap;
    }

    /*private void extraPolateDeliveries() {
        for (Integer weightCategory : monthlyDeliveriesCountPerWeightCategory.keySet()) {
            Map<Integer, Integer> monthlyDeliveries = monthlyDeliveriesCountPerWeightCategory.get(weightCategory);
            MathsProcessingService mathsProcessingService = new MathsProcessingService();
            double[] deliveryForecast = mathsProcessingService.
                    processForecastUsingTripleExponentialTimeSeries(monthlyDeliveries.values().
                                    stream().mapToDouble(subscriptionCount -> Double.parseDouble(subscriptionCount.toString())).toArray()
                            , monthlyDeliveries.size());
            final int totalDeliveryCount =
                    monthlyDeliveries.values().stream().mapToInt(Integer::intValue).sum()
                            + (int) deliveryForecast[0];
            inputDataToCalculatePerProductOpEx.addNumberOfDeliveriesForWeightCategory(weightCategory, totalDeliveryCount);
        }
    }

    private void extraPolateProductSubscription() {
        for (ProductStats productStats : productsStats) {
            MathsProcessingService mathsProcessingService = new MathsProcessingService();
            double[] productSubscriptionForecast = mathsProcessingService.processForecastUsingTripleExponentialTimeSeries(productStats.getSubscriptionsPerMonth().values().
                    stream().mapToDouble(subscriptionCount -> Double.parseDouble(subscriptionCount.toString())).toArray(), productsStats.size());
            final int totalSubscriptionCount =
                    productStats.getSubscriptionsPerMonth().values().stream().mapToInt(Integer::intValue).sum()
                            + (int) productSubscriptionForecast[0];
            inputDataToCalculatePerProductOpEx.addProductSubscription(productStats.getProductId(), totalSubscriptionCount);
            inputDataToCalculatePerProductOpEx.addProductPurchasePrice(productStats.getProductId(), productStats.getPurchasePrice());
        }
    }*/
}
