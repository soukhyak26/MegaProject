package com.affaince.subscription.product.registration.services;

import com.affaince.subscription.product.registration.command.domain.ProductAccount;
import com.affaince.subscription.product.registration.query.view.ProductActualsView;
import com.affaince.subscription.product.registration.query.view.ProductForecastView;

import java.util.List;

/**
 * Created by mandark on 29-04-2016.
 */
public interface ProductDemandForecaster {
    public List<ProductForecastView> forecastDemandGrowthAndChurn(List<ProductActualsView> productActualsViewList);
    public void addNextForecaster(ProductDemandForecaster forecaster);
}
