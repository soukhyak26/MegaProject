package com.affaince.subscription.product.registration.services.forecast;

import com.affaince.subscription.product.registration.query.view.ProductActualMetricsView;
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
    public List<Double> forecastDemandGrowth(List<ProductActualMetricsView> productActuals){
        if(productActuals.size()>6){

        }else{
            if(null != productDemandForecaster) {
                return productDemandForecaster.forecastDemandGrowth(productActuals);
            }
        }
        return null;
    }
    public List<Double> forecastDemandChurn(List<ProductActualMetricsView> productActualMetricsViewList) {
        return null;
    }


}
