package com.affaince.subscription.product.web.request;

import com.affaince.subscription.common.vo.ProductForecastParameter;

/**
 * Created by mandar on 13-09-2016.
 */
public class AddStepForecastParametersRequest {
    private ProductForecastParameter[] productForecastParameters;

    public ProductForecastParameter[] getProductForecastParameters() {
        return productForecastParameters;
    }

    public void setProductForecastParameters(ProductForecastParameter[] productForecastParameters) {
        this.productForecastParameters = productForecastParameters;
    }
}
