package com.affaince.subscription.expensedistribution.processor;

import com.affaince.subscription.common.service.MathsProcessingService;
import com.affaince.subscription.expensedistribution.vo.BasketStats;
import com.affaince.subscription.expensedistribution.vo.InputDataToCalculatePerProductOpEx;
import com.affaince.subscription.expensedistribution.vo.ProductStats;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rsavaliya on 14/2/16.
 */
public class SubscriptionSpecificOperatingExpenseDistributionPreprocessor {
    private static List<ProductStats> productsStats = new ArrayList<>();
    private Map<YearMonth, BasketStats> basketsStats;
    private static InputDataToCalculatePerProductOpEx inputDataToCalculatePerProductOpEx =
            new InputDataToCalculatePerProductOpEx();

    public static void main (String args []) {
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
    }

    public static void generateDataToCalculateSubscriptionSpecificOperationExpensePerProduct () {
        for (ProductStats productStats: productsStats) {
            MathsProcessingService mathsProcessingService = new MathsProcessingService();
            double [] productSubscriptionForecast = mathsProcessingService.processForecastUsingTripleExponentialTimeSeries(productStats.getSubscriptionsPerMonth().values().
                    stream().mapToDouble(subscriptionCount -> Double.parseDouble(subscriptionCount.toString())).toArray(),3);
            final int totalSubscriptionCount=
                productStats.getSubscriptionsPerMonth().values().stream().mapToInt(Integer::intValue).sum()
                        + (int)productSubscriptionForecast[0];
            inputDataToCalculatePerProductOpEx.addProductSubscription(productStats.getProductId(), totalSubscriptionCount);
        }
    }

}
