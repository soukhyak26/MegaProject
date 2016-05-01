package com.affaince.subscription.product.registration.services;

import com.affaince.subscription.product.registration.command.domain.ProductAccount;
import com.affaince.subscription.product.registration.query.view.ProductActualsView;
import com.affaince.subscription.product.registration.query.view.ProductForecastView;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by mandark on 29-04-2016.
 */
public class HistorySizeSpecificProductDemandForecaster implements ProductDemandForecaster {
    private ProductDemandForecaster productDemandForecaster;
    @Autowired
    public HistorySizeSpecificProductDemandForecaster(){}
    public void addNextForecaster(ProductDemandForecaster forecaster){
        this.productDemandForecaster=forecaster;
    }
    public List<ProductForecastView> forecastDemandGrowthAndChurn(List<ProductActualsView> productActuals){
        if(productActuals.size()>6){

        }else{
            if(null != productDemandForecaster) {
                return productDemandForecaster.forecastDemandGrowthAndChurn(productActuals);
            }
        }
        return null;
    }

}
