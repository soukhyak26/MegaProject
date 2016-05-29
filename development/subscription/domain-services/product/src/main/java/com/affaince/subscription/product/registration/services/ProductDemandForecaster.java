package com.affaince.subscription.product.registration.services;

import com.affaince.subscription.product.registration.query.view.ProductActualMetricsView;
import com.affaince.subscription.product.registration.query.view.ProductForecastMetricsView;

import java.util.List;

/**
 * Created by mandark on 29-04-2016.
 */
public interface ProductDemandForecaster {
    public List<ProductForecastMetricsView> forecastDemandGrowthAndChurn(List<ProductActualMetricsView> productActualMetricsViewList);
    public void addNextForecaster(ProductDemandForecaster forecaster);
}
