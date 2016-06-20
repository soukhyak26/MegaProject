package com.affaince.subscription.product.services.forecast;

import com.affaince.subscription.product.query.view.ProductActualMetricsView;

import java.util.List;

/**
 * Created by mandark on 29-04-2016.
 */
public interface ProductDemandForecaster {
    public List<Double> forecastDemandGrowth(List<ProductActualMetricsView> productActualMetricsViewList);
    public List<Double>forecastDemandChurn(List<ProductActualMetricsView> productActualMetricsViewList);
    public void addNextForecaster(ProductDemandForecaster forecaster);
}
