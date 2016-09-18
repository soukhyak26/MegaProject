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
public class OnDeliverySizePaymentStrategyTest {

    private Map<String, Double> deliveryValues;

    @Before
    public void init() {
        deliveryValues = new HashMap<>(10);
        for (int i = 0; i < 10; i++) {
            deliveryValues.put(i + "", 10.0 * (i + 1));
        }
    }

    @Test
    public void testDistributeRewardPoints() {
        PaymentStrategy paymentStrategy = new OnDeliverySizePaymentStrategy();
        Map<String, Double> deliveryWiseRewardsDistributions =
                paymentStrategy.distributeRewardPoints(deliveryValues, 900);

        double totalRewardPoints = deliveryWiseRewardsDistributions.values().
                stream().mapToDouble(i -> i.doubleValue()).sum();

        assertThat(totalRewardPoints, is(900.0));
    }
}
