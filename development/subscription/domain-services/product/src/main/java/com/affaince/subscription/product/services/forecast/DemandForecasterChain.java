package com.affaince.subscription.product.services.forecast;

import com.affaince.subscription.common.type.PeriodUnit;
import com.affaince.subscription.product.query.repository.ProductActualMetricsViewRepository;
import com.affaince.subscription.product.query.repository.ProductForecastMetricsViewRepository;
import com.affaince.subscription.product.query.view.ProductActualMetricsView;
import com.affaince.subscription.product.vo.DemandGrowthAndChurnForecast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mandark on 01-05-2016.
 */

public class DemandForecasterChain {
    @Autowired
    private ProductForecastMetricsViewRepository productForecastMetricsViewRepository;
    @Autowired
    private ProductActualMetricsViewRepository productActualMetricsViewRepository;

    private ProductDemandForecaster initialForecaster;

    @Autowired
    SimpleMovingAverageDemandForecaster simpleMovingAverageDemandForecaster;
    @Autowired
    SimpleExponentialSmoothingDemandForecaster simpleExponentialSmoothingDemandForecaster;
    @Autowired
    TripleExponentialSmoothingDemandForecaster tripleExponentialSmoothingDemandForecaster;
    @Autowired
    ARIMABasedDemandForecaster arimaBasedDemandForecaster;

    @Value("${forecaster.chain.list}")
    private String forecasterChainElements;

    public DemandForecasterChain() {
    }

    @PostConstruct
    public void init(){
        List<String> forecasterPrefixes = Arrays.asList(forecasterChainElements.split(","));
        for (String prefix : forecasterPrefixes) {

            if (prefix.equals("sma")) {
                this.addForecaster(simpleMovingAverageDemandForecaster);
            } else if (prefix.equals("sema")) {
                this.addForecaster(simpleExponentialSmoothingDemandForecaster);
            } else if (prefix.equals("tema")) {
                this.addForecaster(tripleExponentialSmoothingDemandForecaster);
            } else {
                this.addForecaster(arimaBasedDemandForecaster);
            }
        }

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
        List<Double> forecastTotalSubscriptions = initialForecaster.forecastDemandGrowth(productActualMetricsViewList);
        //forecast churned subscriptions for next period
        List<Double> forecastChurnedSubscriptions = initialForecaster.forecastDemandChurn(productActualMetricsViewList);
        return new DemandGrowthAndChurnForecast(Double.valueOf(forecastTotalSubscriptions.get(0)).longValue(), Double.valueOf(forecastChurnedSubscriptions.get(0)).longValue());
    }


    public DemandGrowthAndChurnForecast forecast(List<ProductActualMetricsView> productActualMetricsViewList) {
        //Forecast total subscriptions for next period
        List<Double> forecastTotalSubscriptions = initialForecaster.forecastDemandGrowth(productActualMetricsViewList);
        //forecast churned subscriptions for next period
        List<Double> forecastChurnedSubscriptions = initialForecaster.forecastDemandChurn(productActualMetricsViewList);
        return new DemandGrowthAndChurnForecast(Double.valueOf(forecastTotalSubscriptions.get(0)).longValue(), Double.valueOf(forecastChurnedSubscriptions.get(0)).longValue());
    }

}
