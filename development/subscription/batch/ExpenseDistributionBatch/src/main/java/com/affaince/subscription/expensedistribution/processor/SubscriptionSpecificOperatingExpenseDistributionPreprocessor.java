package com.affaince.subscription.expensedistribution.processor;

import com.affaince.subscription.common.service.MathsProcessingService;
import com.affaince.subscription.expensedistribution.vo.InputDataToCalculatePerProductOpEx;
import com.affaince.subscription.expensedistribution.vo.ProductStats;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by rsavaliya on 14/2/16.
 */
public class SubscriptionSpecificOperatingExpenseDistributionPreprocessor implements Processor {
    private List<ProductStats> productsStats = new ArrayList<>();
    // Map of <weight, map of <month, no of basket delivered>>
    private Map<Integer, Map<Integer, Integer>> monthlyDeliveriesCountPerWeightCategory;
    private InputDataToCalculatePerProductOpEx inputDataToCalculatePerProductOpEx =
            new InputDataToCalculatePerProductOpEx();

    /*public static void main (String args []) {
        ProductStats productStats = new ProductStats();
        productStats.setProductId("1");
        productStats.setPurchasePrice(20);
        Map <Integer, Integer> subscriptionsPerMonth = new HashMap<>();
        subscriptionsPerMonth.put(1, 30);
        subscriptionsPerMonth.put(2, 50);
        subscriptionsPerMonth.put(3, 25);
        productStats.setSubscriptionsPerMonth(subscriptionsPerMonth);
        productsStats.add(productStats);
        generateDataToCalculateSubscriptionSpecificOperationExpensePerProduct();
    }*/

    @Override
    public void process(Exchange exchange) throws Exception {
        extraPolateProductSubscription();
        extraPolateDeliveries ();
        exchange.getIn().setBody(inputDataToCalculatePerProductOpEx, inputDataToCalculatePerProductOpEx.getClass());
    }

    private void extraPolateDeliveries() {
        for (Integer weightCategory: monthlyDeliveriesCountPerWeightCategory.keySet()) {
            Map<Integer, Integer> monthlyDeliveries = monthlyDeliveriesCountPerWeightCategory.get(weightCategory);
            MathsProcessingService mathsProcessingService = new MathsProcessingService();
            double [] deliveryForecast = mathsProcessingService.
                    processForecastUsingTripleExponentialTimeSeries(monthlyDeliveries.values().
                    stream().mapToDouble(subscriptionCount -> Double.parseDouble(subscriptionCount.toString())).toArray()
                    ,monthlyDeliveries.size());
            final int totalDeliveryCount=
                    monthlyDeliveries.values().stream().mapToInt(Integer::intValue).sum()
                            + (int)deliveryForecast[0];
            inputDataToCalculatePerProductOpEx.addNumberOfDeliveriesForWeightCategory(weightCategory, totalDeliveryCount);
        }
    }

    private void extraPolateProductSubscription() {
        for (ProductStats productStats: productsStats) {
            MathsProcessingService mathsProcessingService = new MathsProcessingService();
            double [] productSubscriptionForecast = mathsProcessingService.processForecastUsingTripleExponentialTimeSeries(productStats.getSubscriptionsPerMonth().values().
                    stream().mapToDouble(subscriptionCount -> Double.parseDouble(subscriptionCount.toString())).toArray(),productsStats.size());
            final int totalSubscriptionCount=
                productStats.getSubscriptionsPerMonth().values().stream().mapToInt(Integer::intValue).sum()
                        + (int)productSubscriptionForecast[0];
            inputDataToCalculatePerProductOpEx.addProductSubscription(productStats.getProductId(), totalSubscriptionCount);
            inputDataToCalculatePerProductOpEx.addProductPurchasePrice(productStats.getProductId(), productStats.getPurchasePrice());
        }
    }
}
