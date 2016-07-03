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
    private final ProductForecastMetricsViewRepository productForecastMetricsViewRepository;
    private final ProductActualMetricsViewRepository productActualMetricsViewRepository;
    private final ProductViewRepository productViewRepository;
    private ProductDemandForecaster initialForecaster;


    @Autowired
    public DemandForecasterChain(ProductForecastMetricsViewRepository productForecastMetricsViewRepository, ProductActualMetricsViewRepository productActualMetricsViewRepository, ProductViewRepository productViewRepository) {
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
    //forecasting for all products
    public void forecast(ProductView productView) {
       // Iterable<ProductView> products = productViewRepository.findAll();

/*
        for (ProductView productView:
             products) {
*/
            List<ProductActualMetricsView> productActualMetricsViewList =
                    productActualMetricsViewRepository.findByProductVersionId_ProductId(productView.getProductId());
            List<Double> forecastViews=initialForecaster.forecastDemandGrowth(productActualMetricsViewList);
            Sort sort = new Sort(Sort.Direction.DESC, "productVersionId.fromDate");
            ProductForecastMetricsView latestProductForecastMetricsView= productForecastMetricsViewRepository.findByProductVersionId_ProductId(productView.getProductId(),sort).get(0);
            latestProductForecastMetricsView.setEndDate(LocalDate.now());
            ProductForecastMetricsView newProductForecastMetricsView= new ProductForecastMetricsView(new ProductVersionId(productView.getProductId(), LocalDate.now()),new LocalDate(9999,12,31));
            newProductForecastMetricsView.setTotalNumberOfExistingSubscriptions(Double.valueOf(forecastViews.get(0)).longValue());
            productForecastMetricsViewRepository.save(newProductForecastMetricsView);
//        }
    }


}
