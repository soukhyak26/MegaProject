package com.affaince.subscription.product.web.request;

import com.affaince.subscription.common.vo.ProductForecastParameter;

/**
 * Created by rbsavaliya on 05-12-2015.
 */
public class AddForecastParametersRequest {
    private ProductForecastParameter[] productForecastParameters;

    public AddForecastParametersRequest() {
    }

    public ProductForecastParameter[] getProductForecastParameters() {
        return productForecastParameters;
    }

    public void setProductForecastParameters(ProductForecastParameter[] productForecastParameters) {
        this.productForecastParameters = productForecastParameters;
    }

}
