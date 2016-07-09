package com.affaince.subscription.product.services.forecast;

import com.affaince.subscription.product.query.repository.ProductActualMetricsViewRepository;
import com.affaince.subscription.product.query.repository.ProductForecastMetricsViewRepository;
import com.affaince.subscription.product.query.view.ProductActualMetricsView;
import com.affaince.subscription.product.vo.DemandGrowthAndChurnForecast;
import com.affaince.subscription.product.vo.ForecastersList;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;

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
    private ForecastersList forecastersList;

    @Value("${forecaster.chain.list}")
    private String forecasterChainElements;

    @Autowired
    public DemandForecasterChain() {

    }

    public DemandForecasterChain buildForecasterChain(ProductForecastMetricsViewRepository productForecastMetricsViewRepository, ProductActualMetricsViewRepository productActualMetricsViewRepository) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        this.productForecastMetricsViewRepository = productForecastMetricsViewRepository;
        this.productActualMetricsViewRepository = productActualMetricsViewRepository;
        List<String> forecasterPrefixes = Arrays.asList(forecasterChainElements.split(","));
        Map<String, String> forecasters = forecastersList.getElements();
        Iterator<Map.Entry<String, String>> iterator = forecasters.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> forecasterEntry = iterator.next();
            if (forecasterPrefixes.contains(forecasterEntry.getKey())) {
                ProductDemandForecaster forecaster = (ProductDemandForecaster) BeanUtils.instantiate(Class.forName(forecasterEntry.getValue()));
                this.addForecaster(forecaster);
            }

        }
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
        List<Double> forecastTotalSubscriptions = initialForecaster.forecastDemandGrowth(productActualMetricsViewList);
        //forecast new subscriptions for next period
        List<Double> forecastChurnedSubscriptions = initialForecaster.forecastDemandChurn(productActualMetricsViewList);
        return new DemandGrowthAndChurnForecast(Double.valueOf(forecastTotalSubscriptions.get(0)).longValue(), Double.valueOf(forecastChurnedSubscriptions.get(0)).longValue());
    }


}
