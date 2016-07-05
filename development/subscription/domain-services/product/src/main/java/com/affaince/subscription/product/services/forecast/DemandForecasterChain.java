package com.affaince.subscription.product.services.forecast;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.query.repository.ProductActualMetricsViewRepository;
import com.affaince.subscription.product.query.repository.ProductForecastMetricsViewRepository;
import com.affaince.subscription.product.query.repository.ProductViewRepository;
import com.affaince.subscription.product.query.view.ProductActualMetricsView;
import com.affaince.subscription.product.query.view.ProductForecastMetricsView;
import com.affaince.subscription.product.query.view.ProductView;
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

    public DemandForecasterChain(ProductForecastMetricsViewRepository productForecastMetricsViewRepository, ProductActualMetricsViewRepository productActualMetricsViewRepository) {
        this.productForecastMetricsViewRepository = productForecastMetricsViewRepository;
        this.productActualMetricsViewRepository = productActualMetricsViewRepository;
        //this.productViewRepository = productViewRepository;
    }

    public void addForecaster(ProductDemandForecaster forecaster) {
        if (null != initialForecaster) {
            initialForecaster.addNextForecaster(forecaster);
        } else {
            initialForecaster = forecaster;
        }
    }
    //forecasting for each product
    public void forecast(String productId) {
            List<ProductActualMetricsView> productActualMetricsViewList =
                    productActualMetricsViewRepository.findByProductVersionId_ProductId(productId);
            //Forecast total subscriptions for next period
            List<Double> forecastTotalSubscriptions=initialForecaster.forecastDemandGrowth(productActualMetricsViewList);
            //forecast new subscriptions for next period
            List<Double> forecastChurnedSubscriptions= initialForecaster.forecastDemandChurn(productActualMetricsViewList);
            Sort sort = new Sort(Sort.Direction.DESC, "productVersionId.fromDate");
            ProductForecastMetricsView latestProductForecastMetricsView= productForecastMetricsViewRepository.findByProductVersionId_ProductId(productId,sort).get(0);
            latestProductForecastMetricsView.setEndDate(LocalDate.now());
            //forecasted new subscriptions per period= (new forecasted total subscription - latest forecasted total subscription + new forecasted churned subscriptions)
            double newSubscriptions = forecastTotalSubscriptions.get(0) - latestProductForecastMetricsView.getTotalNumberOfExistingSubscriptions() +  forecastChurnedSubscriptions.get(0);
            ProductForecastMetricsView newProductForecastMetricsView= new ProductForecastMetricsView(new ProductVersionId(productId, LocalDate.now()),new LocalDate(9999,12,31));
            newProductForecastMetricsView.setTotalNumberOfExistingSubscriptions(Double.valueOf(forecastTotalSubscriptions.get(0)).longValue());
            newProductForecastMetricsView.setChurnedSubscriptions(Double.valueOf(forecastChurnedSubscriptions.get(0)).longValue());
            newProductForecastMetricsView.setNewSubscriptions(Double.valueOf(newSubscriptions).longValue());
            productForecastMetricsViewRepository.save(newProductForecastMetricsView);
    }


}
