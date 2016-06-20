package com.affaince.subscription.product.services.forecast;

import com.affaince.subscription.product.query.repository.ProductActualMetricsViewRepository;
import com.affaince.subscription.product.query.repository.ProductForecastMetricsViewRepository;
import com.affaince.subscription.product.query.repository.ProductViewRepository;
import com.affaince.subscription.product.query.view.ProductActualMetricsView;
import com.affaince.subscription.product.query.view.ProductView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by mandark on 01-05-2016.
 */
public class DemandForecasterChain {
    private final ProductForecastMetricsViewRepository productForecastMetricsViewRepository;
    private final ProductActualMetricsViewRepository productActualMetricsViewRepository;
    private final ProductViewRepository productViewRepository;
    private ProductDemandForecaster initialForecaster;


    @Autowired
    DemandForecasterChain(ProductForecastMetricsViewRepository productForecastMetricsViewRepository, ProductActualMetricsViewRepository productActualMetricsViewRepository, ProductViewRepository productViewRepository) {
        this.productForecastMetricsViewRepository = productForecastMetricsViewRepository;
        this.productActualMetricsViewRepository = productActualMetricsViewRepository;
        this.productViewRepository = productViewRepository;
    }

    public void addForecaster(ProductDemandForecaster forecaster) {
        if (null != initialForecaster) {
            initialForecaster.addNextForecaster(forecaster);
        } else {
            initialForecaster = forecaster;
        }
    }

    public void forecast() {
        Iterable<ProductView> products = productViewRepository.findAll();

        for (ProductView productView:
             products) {
            List<ProductActualMetricsView> productActualMetricsViewList =
                    productActualMetricsViewRepository.findByProductId(productView.getProductId());
            List<Double> forecastViews=initialForecaster.forecastDemandGrowth(productActualMetricsViewList);
        }
    }
}
