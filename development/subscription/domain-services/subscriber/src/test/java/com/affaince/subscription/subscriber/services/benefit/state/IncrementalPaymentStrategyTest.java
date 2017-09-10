package com.affaince.subscription.subscriber.services.benefit.state;

import com.affaince.subscription.subscriber.services.benefit.context.BenefitCalculationRequest;
import com.affaince.subscription.subscriber.services.benefit.context.BenefitExecutionContext;
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
        BenefitCalculationRequest request = new BenefitCalculationRequest();
        request.setDeliveryAmounts(deliveryValuesEven);
        request.setRewardPointAdjustment(0);
        BenefitExecutionContext benefitExecutionContext = new BenefitExecutionContext();
        benefitExecutionContext.setRequest(request);
        benefitExecutionContext.addRewardPoints(900);
        PaymentStrategy paymentStrategy = new IncrementalPaymentStrategy();
        paymentStrategy.distributeRewardPoints(benefitExecutionContext);

        double totalRewardPoints = benefitExecutionContext.getRewardPointsDistribution().values()
                .stream().mapToDouble(i -> i.doubleValue()).sum();

        assertThat(totalRewardPoints, is(900.0));
    }

    @Test
    public void testDistributeRewardPointsForOddNOOfDeliveries() {
        BenefitCalculationRequest request = new BenefitCalculationRequest();
        request.setDeliveryAmounts(deliveryValuesOdd);
        request.setRewardPointAdjustment(0);
        BenefitExecutionContext benefitExecutionContext = new BenefitExecutionContext();
        benefitExecutionContext.setRequest(request);
        benefitExecutionContext.addRewardPoints(900);
        PaymentStrategy paymentStrategy = new IncrementalPaymentStrategy();
        paymentStrategy.distributeRewardPoints(benefitExecutionContext);

        double totalRewardPoints = benefitExecutionContext.getRewardPointsDistribution().values()
                .stream().mapToDouble(i -> i.doubleValue()).sum();

        assertThat(totalRewardPoints, is(900.0));
    }
}
