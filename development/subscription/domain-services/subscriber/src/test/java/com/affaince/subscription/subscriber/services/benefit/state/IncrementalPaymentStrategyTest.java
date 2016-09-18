package com.affaince.subscription.subscriber.services.benefit.state;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by rbsavaliya on 18-09-2016.
 */
public class IncrementalPaymentStrategyTest {

    private Map<String, Double> deliveryValuesEven;
    private Map<String, Double> deliveryValuesOdd;

    @Before
    public void init() {
        deliveryValuesEven = new HashMap<>(10);
        deliveryValuesOdd = new HashMap<>(9);
        for (int i = 0; i < 10; i++) {
            deliveryValuesEven.put(i + "", 10.0 * (i + 1));
        }
        for (int i = 0; i < 9; i++) {
            deliveryValuesOdd.put(i + "", 10.0 * (i + 1));
        }
    }

    @Test
    public void testDistributeRewardPointsForEvenNOOfDeliveries() {
        PaymentStrategy paymentStrategy = new IncrementalPaymentStrategy();
        Map<String, Double> deliveryWiseRewardsDistributions =
                paymentStrategy.distributeRewardPoints(deliveryValuesEven, 900);

        double totalRewardPoints = deliveryWiseRewardsDistributions.values().
                stream().mapToDouble(i -> i.doubleValue()).sum();

        assertThat(totalRewardPoints, is(900.0));
    }

    @Test
    public void testDistributeRewardPointsForOddNOOfDeliveries() {
        PaymentStrategy paymentStrategy = new IncrementalPaymentStrategy();
        Map<String, Double> deliveryWiseRewardsDistributions =
                paymentStrategy.distributeRewardPoints(deliveryValuesOdd, 900);

        double totalRewardPoints = deliveryWiseRewardsDistributions.values().
                stream().mapToDouble(i -> i.doubleValue()).sum();

        assertThat(totalRewardPoints, is(900.0));
    }
}
