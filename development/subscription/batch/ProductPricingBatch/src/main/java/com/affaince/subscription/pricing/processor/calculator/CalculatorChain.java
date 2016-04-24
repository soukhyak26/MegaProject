package com.affaince.subscription.pricing.processor.calculator;

import com.affaince.subscription.pricing.query.view.PriceBucketView;
import com.affaince.subscription.pricing.query.view.ProductStatisticsView;

import java.util.List;

/**
 * Created by mandark on 27-03-2016.
 */
public class CalculatorChain {
    private AbstractPriceCalculator initialCalculator;

    public void addCalculator(AbstractPriceCalculator calculator) {
        if (initialCalculator != null) {
            initialCalculator.setNextCalculator(calculator);
        }
        initialCalculator = calculator;
    }

    public PriceBucketView calculatePrice(String productId, List<PriceBucketView> activePriceBuckets, ProductStatisticsView productStatisticsView) {
        return initialCalculator.calculatePrice(productId, activePriceBuckets, productStatisticsView);
    }
}
