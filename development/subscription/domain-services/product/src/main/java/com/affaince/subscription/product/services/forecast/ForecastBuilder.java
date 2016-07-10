package com.affaince.subscription.product.services.forecast;

import com.affaince.subscription.product.query.repository.ProductActualMetricsViewRepository;
import com.affaince.subscription.product.query.repository.ProductApproximateForecastViewRepository;
import com.affaince.subscription.product.query.repository.ProductForecastMetricsViewRepository;
import com.affaince.subscription.product.query.view.ProductActualMetricsView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by mandar on 10-07-2016.
 */
public class ForecastBuilder {

    @Autowired
    private ProductForecastMetricsViewRepository productForecastMetricsViewRepository;
    @Autowired
    private ProductActualMetricsViewRepository productActualMetricsViewRepository;

    @Autowired
    private ProductApproximateForecastViewRepository productApproximateForecastViewRepository;
    public void buildForecastForLongFuture(String productId){
        List<ProductActualMetricsView> productActualMetricsViewList =
                productActualMetricsViewRepository.findByProductVersionId_ProductId(productId);

    }

    public void buidlForecastForShortFuture(){

    }
}
