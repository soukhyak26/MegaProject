package com.affaince.subscription.product.registration.services;

import com.affaince.subscription.product.registration.command.domain.ProductAccount;
import com.affaince.subscription.product.registration.query.repository.ProductActualsViewRepository;
import com.affaince.subscription.product.registration.query.repository.ProductForecastViewRepository;
import com.affaince.subscription.product.registration.query.view.ProductActualsView;
import com.affaince.subscription.product.registration.query.view.ProductForecastView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by mandark on 01-05-2016.
 */
public class MinimumDataSetDemandForecaster implements ProductDemandForecaster {

    private ProductDemandForecaster productDemandForecaster;
    @Autowired
    public MinimumDataSetDemandForecaster() {
    }

    public void addNextForecaster(ProductDemandForecaster forecaster){
        this.productDemandForecaster=forecaster;
    }
    public List<ProductForecastView> forecastDemandGrowthAndChurn(List<ProductActualsView> productActualsViewList){
        if(productActualsViewList.size()<=6){

        }else{
            if(null != productDemandForecaster) {
                return productDemandForecaster.forecastDemandGrowthAndChurn(productActualsViewList);
            }
        }
        return null;
    }

}
