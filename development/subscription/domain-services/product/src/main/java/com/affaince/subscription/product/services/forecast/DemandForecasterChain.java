package com.affaince.subscription.product.services.forecast;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.query.repository.ProductActualMetricsViewRepository;
import com.affaince.subscription.product.query.repository.ProductForecastMetricsViewRepository;
import com.affaince.subscription.product.query.repository.ProductViewRepository;
import com.affaince.subscription.product.query.view.ProductActualMetricsView;
import com.affaince.subscription.product.query.view.ProductForecastMetricsView;
import com.affaince.subscription.product.query.view.ProductView;
import com.affaince.subscription.product.vo.DemandGrowthAndChurnForecast;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by mandark on 01-05-2016.
 */

public class DemandForecasterChain {
    @Autowired
    private ProductForecastMetricsViewRepository productForecastMetricsViewRepository;
    @Autowired
    private ProductActualMetricsViewRepository productActualMetricsViewRepository;
    //private final ProductViewRepository productViewRepository;
    private ProductDemandForecaster initialForecaster;

    @Autowired
    public DemandForecasterChain(){

    }

    public DemandForecasterChain buildForecasterChain(ProductForecastMetricsViewRepository productForecastMetricsViewRepository, ProductActualMetricsViewRepository productActualMetricsViewRepository) {
        this.productForecastMetricsViewRepository = productForecastMetricsViewRepository;
        this.productActualMetricsViewRepository = productActualMetricsViewRepository;
        ProductDemandForecaster forecaster1 = new SimpleMovingAverageDemandForecaster();
        ProductDemandForecaster forecaster2 = new SimpleExponentialSmoothingDemandForecaster();
        ProductDemandForecaster forecaster3 = new TripleExponentialSmoothingDemandForecaster();
        ProductDemandForecaster forecaster4 = new ARIMABasedDemandForecaster();
        forecaster1.addNextForecaster(forecaster2);
        forecaster2.addNextForecaster(forecaster3);
        forecaster3.addNextForecaster(forecaster4);
        this.addForecaster(forecaster1);
        return this;
    }

    private void addForecaster(ProductDemandForecaster forecaster) {
        if (null != initialForecaster) {
            initialForecaster.addNextForecaster(forecaster);
        } else {
            initialForecaster = forecaster;
        }
    }
    //forecasting for each product
    public DemandGrowthAndChurnForecast forecast(String productId) {
            List<ProductActualMetricsView> productActualMetricsViewList =
                    productActualMetricsViewRepository.findByProductVersionId_ProductId(productId);
            //Forecast total subscriptions for next period
            List<Double> forecastTotalSubscriptions=initialForecaster.forecastDemandGrowth(productActualMetricsViewList);
            //forecast new subscriptions for next period
            List<Double> forecastChurnedSubscriptions= initialForecaster.forecastDemandChurn(productActualMetricsViewList);
            return new DemandGrowthAndChurnForecast(Double.valueOf(forecastTotalSubscriptions.get(0)).longValue(),Double.valueOf(forecastChurnedSubscriptions.get(0)).longValue());
    }


}
