package com.affaince.subscription.product.registration.services.pricing.processor.calculator;

import com.affaince.subscription.product.registration.query.view.PriceBucketView;
import com.affaince.subscription.product.registration.query.view.ProductActualMetricsView;
import com.affaince.subscription.product.registration.query.view.ProductForecastMetricsView;

import java.util.List;

/**
 * Created by mandark on 27-03-2016.
 */
public class CalculatorChain {
    private AbstractPriceCalculator initialCalculator;

    public void addCalculator(AbstractPriceCalculator calculator) {
        if (initialCalculator != null) {
            initialCalculator.setNextCalculator(calculator);
        }else {
            initialCalculator = calculator;
        }
    }

    public PriceBucketView calculatePrice(List<PriceBucketView> activePriceBuckets, ProductActualMetricsView productActualMetricsView, ProductForecastMetricsView productForecastMetricsView) {
        return initialCalculator.calculatePrice(activePriceBuckets, productActualMetricsView,productForecastMetricsView);
    }
}
