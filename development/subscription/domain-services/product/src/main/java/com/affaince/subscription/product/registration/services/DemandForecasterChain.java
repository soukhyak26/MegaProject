package com.affaince.subscription.product.registration.services;

import com.affaince.subscription.product.registration.query.repository.ProductActualsViewRepository;
import com.affaince.subscription.product.registration.query.repository.ProductForecastViewRepository;
import com.affaince.subscription.product.registration.query.repository.ProductViewRepository;
import com.affaince.subscription.product.registration.query.view.ProductActualsView;
import com.affaince.subscription.product.registration.query.view.ProductForecastView;
import com.affaince.subscription.product.registration.query.view.ProductView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by mandark on 01-05-2016.
 */
public class DemandForecasterChain {
    private final ProductForecastViewRepository productForecastViewRepository;
    private final ProductActualsViewRepository productActualsViewRepository;
    private final ProductViewRepository productViewRepository;
    private ProductDemandForecaster initialForecaster;


    @Autowired
    DemandForecasterChain(ProductForecastViewRepository productForecastViewRepository,ProductActualsViewRepository productActualsViewRepository, ProductViewRepository productViewRepository) {
        this.productForecastViewRepository=productForecastViewRepository;
        this.productActualsViewRepository = productActualsViewRepository;
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
            List<ProductActualsView> productActualsViewList =
                    productActualsViewRepository.findByProductId(productView.getProductId());
            List<ProductForecastView> forecastViews=initialForecaster.forecastDemandGrowthAndChurn(productActualsViewList);
        }
    }
}
