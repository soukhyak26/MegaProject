package com.affaince.subscription.product.services.forecast;

import com.affaince.subscription.product.query.repository.*;
import com.affaince.subscription.product.query.view.ProductActualMetricsView;
import com.affaince.subscription.product.query.view.ProductActualsView;
import org.joda.time.LocalDate;
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

    @Autowired
    private ProductForecastViewRepository productForecastViewRepository;

    @Autowired
    private ProductActualsViewRepository productActualsViewRepository;

    public void buildForecastForLongFuture(String productId) {
        List<ProductActualMetricsView> productActualMetricsViewList =
                productActualMetricsViewRepository.findByProductVersionId_ProductId(productId);

    }

    public void aggregateActuals(String productId) {
    }

    public void buildForecastForShortFuture() {

    }
}
